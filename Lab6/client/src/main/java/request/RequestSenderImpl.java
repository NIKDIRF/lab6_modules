package request;

import studygroup.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.channels.SocketChannel;

public class RequestSenderImpl implements RequestSender{
    private OutputStream stream;

    public void sendRequest(SocketChannel socketChannel, Request request) throws IOException {
        stream = socketChannel.socket().getOutputStream();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(byteStream);
        stream.writeObject(request);
        this.stream.write(byteStream.toByteArray());
        this.stream.flush();
    }
}
