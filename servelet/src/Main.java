import com.sun.org.apache.bcel.internal.generic.NEW;
import java.util.Random;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel=null;
       try {
            socketChannel=SocketChannel.open();
       }catch (Exception e){
           System.out.println("openerro");
       }
       try {
           socketChannel.configureBlocking(false);
           socketChannel.connect(new InetSocketAddress("localhost",1234));
       }catch (IOException e){
           System.out.println("connect erro");
       }
       try {
        if(socketChannel.finishConnect())
        {
            Random i = new Random();

            while(true)
            {
                TimeUnit.SECONDS.sleep(1);
                buffer.clear();
                buffer.putInt(18+i);
                buffer.putInt(5+i);
                buffer.putFloat(8.9f+i);
                buffer.putFloat(15.65f+i);
                buffer.putFloat(73.8f+i);
                buffer.flip();
                i++;
                while(buffer.hasRemaining()) {
                    try {
                        socketChannel.write(buffer);
                    }catch (IOException e){
                        System.out.println("write error");
                    }
                }
                System.out.println(i);
            }
        }
    }
        catch (Exception e)
    {
        e.printStackTrace();
    }
        finally{
        try{
            if(socketChannel!=null){
                socketChannel.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    }
}
