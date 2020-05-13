import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 *  @Description:
 *  @author:xuyaqi
 *  @date:2018年6月6日
 */
public class Server {
    private static final int BUF_SIZE = 1024;
    private static final int PORT = 1234;
    private ServerSocketChannel servSocketChannel = null;
    private Selector selector = null;
    static private Tool tool;
    public static void main(String[] args) {
        new Server().startServer();

    }

    public void startServer() {
        try {
            servSocketChannel = ServerSocketChannel.open();
            // 设置为非阻塞
            servSocketChannel.configureBlocking(false);
            // 绑定端口
            servSocketChannel.socket().bind(new InetSocketAddress(PORT));
            selector = Selector.open();
            // 注册监听事件
            servSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void doGet(){
        URL url;
        HttpURLConnection uRLConnection;
        String getUrl = "http://localhost:9999/demo/reflush" ;
        try {
            url = new URL(getUrl);
            uRLConnection = (HttpURLConnection)url.openConnection();
            uRLConnection.getInputStream();
            uRLConnection.disconnect();
            return ;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ;
        } catch (IOException e) {
            e.printStackTrace();
            return ;
        }
    }
    private void listen() {
        tool=new Tool("123456","localhost");
        while (true) {
            try {
                //
                selector.select();
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isValid() && key.isAcceptable()) {
                        System.out.println("handleAccept");
                        handleAccept(key);
                    }
                    if (key.isValid() && key.isReadable()) {
                        System.out.println("handleRead");
                        handleRead(key);
                    }
//                    if (key.isValid() && key.isWritable()) {
//                        System.out.println("handleWrite");
//                        handleWrite(key);
//                    }
                    if (key.isValid() && key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    iter.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocateDirect(BUF_SIZE));
    }

    public static void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        try {
            ByteBuffer buf = (ByteBuffer) key.attachment();

//            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            int bytesRead = sc.read(buf);
            if (bytesRead > 0) {
                buf.flip();
                int system_num=buf.getInt();
                int dev_num=buf.getInt();
                float temp=buf.getFloat();
                float humi=buf.getFloat();
                float smoke=buf.getFloat();
                String name=String.valueOf(system_num)+"_"+String.valueOf(dev_num);
                tool.set(name+"_temp",String.valueOf(temp),1);
                tool.set(name+"_humi",String.valueOf(humi),2);
                tool.set(name+"_smoke",String.valueOf(smoke),3);
                System.out.println(name);
                buf.clear();
                doGet();

            } else {
                System.out.println("关闭的连接");
                key.cancel();
                sc.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            key.cancel();
            sc.close();
        }

    }
}