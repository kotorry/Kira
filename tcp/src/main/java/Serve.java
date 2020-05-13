import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class Serve {
    public int port;
    public static int data_size=1024;
    private Tool tool;
    private String local;
    private String password;
    Serve(int p,String pw,String l){
        port=p;
        local=l;
        password=pw;
    }
    public void address(SelectionKey key)throws IOException{
        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(data_size));
        System.out.println("以链接");
    }
    public void read(SelectionKey key)throws IOException{
        SocketChannel sc=(SocketChannel)key.channel();
        ByteBuffer buf=(ByteBuffer) key.attachment();
        long size=sc.read(buf);
        if(size>0)
        {
            buf.flip();
            int system_num=buf.getInt();
            int dev_num=buf.getInt();
            float temp=buf.getFloat();
            float humi=buf.getFloat();
            float smoke=buf.getFloat();
//            buf.get(system_num,0,4);
//            System.out.println(buf.getInt());
            String name=String.valueOf(system_num)+"_"+String.valueOf(dev_num);
            System.out.println(name);
            buf.clear();
            tool.set(name+"_temp",String.valueOf(temp),1);
            tool.set(name+"_humi",String.valueOf(humi),2);
            tool.set(name+"_smoke",String.valueOf(smoke),3);
        }
//        doGet();
        sc.close();
    }
    public void start() {
        tool=new Tool(password,local);
        Selector mSelector = null;//用于注册所有连接到服务器的SocketChannel对象
        ServerSocketChannel server = null;
        try {
            mSelector = Selector.open();
        } catch (IOException e) {
            System.out.println("selector_open失败");
            e.printStackTrace();
        }
        try {
            server = ServerSocketChannel.open();
        } catch (IOException e) {
            System.out.println("server_open失败");
            e.printStackTrace();
        }
        InetSocketAddress isa = new InetSocketAddress(port);
        try {
            server.socket().bind(isa);//绑定指定端口
            server.configureBlocking(false);
            server.register(mSelector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println("server启动失败");
            e.printStackTrace();
        }
        System.out.println("服务器在" + port + "端口启动成功");
        try {
            while (mSelector.select() > 0) {
                Iterator<SelectionKey> keyIterator = mSelector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey sk = keyIterator.next();
                    if (sk.isAcceptable()) {
                       address(sk);
                    }
                    if (sk.isReadable())//有数据
                    {
                        read(sk);
                    }
                    keyIterator.remove();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("错误");
        }
        finally {
            try {
                mSelector.close();
                server.close();
            }catch (IOException E){
                E.printStackTrace();
            }
        }
    }
    public void doGet(){
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
}
