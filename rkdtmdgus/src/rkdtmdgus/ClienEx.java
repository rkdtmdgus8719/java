import java.io.*;
import java.net.*;
import java.util.*;

public class ClienEx{
  public static void main(String[] args) {
	BufferedReader in = null;
	BufferedWriter out = null;
	Socket socket = null;
	Scanner scanner = new Scanner(System.in); // 키보드에서 읽을 scanner 객체 생성
	try {
		socket = new Socket("localhost",9999); // 클라이언트 소켓 생성.서버에 연결
		in = new BufferedReader(new InputStreamReader(socket.getlnputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		while (true) {
		  System.out.print("보느기>>");//프롬프트
		  String outputMessage = scanner.nextLine();//키보드에서 한 행 읽기
		  if (outputMessage.equalsIgnoreCase("bye")) {
			  out.write(outputMessage+"wn");//"bye"문자열 전송
			  out.flush();
			  break;// 사용자가 "bye"를 입력한 경우 서버로 전송 후 실행 종료
		  }
		  out.write(outputMessage+"wn");//키보드에서 읽은 문자열 전송
		  out.flush();//out의 스트림 버퍼에 있는 모든 문자열 전송
		  String inputMessage = in.readLine();//서버로부터 한 행 수신
		  System.out.println("서버:"+ inputMessage);
		}
	}catch (IOException e) {
	   System.out.println(e.getMessage());
	}finally {
		try {
			scanner.close();
			if(socket != null) socket.close(); // 클라이언트 소케 닫기
		} catch (IOException e) {
			System.out.println("서버와 채팅 중 오류가 발생했습니다.");
		}
	}
  }
}