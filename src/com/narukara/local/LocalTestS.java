package com.narukara.local;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class LocalTestS {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			//49.235.143.220
			//127.0.0.1
			Socket s = new Socket("49.235.143.220", 7001);
//			scanner.nextLine();
			System.out.println("Connected");
			System.out.print("id:");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			new Thread() {
				@Override
				public void run() {
					boolean aflag = true;
					try {
						while (true) {
							System.out.println(">" + bufferedReader.readLine());
							if(aflag) {
								bufferedWriter.write("Connected\n");
								bufferedWriter.flush();
								aflag = false;
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();

			bufferedWriter.write("cn\n");
			while (true) {
				bufferedWriter.write(scanner.nextLine() + "\n");
				bufferedWriter.flush();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
