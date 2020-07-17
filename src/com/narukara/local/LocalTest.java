package com.narukara.local;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class LocalTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			Socket s = new Socket("49.235.143.220", 7000);
			System.out.println("Connected");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			new Thread() {
				@Override
				public void run() {
					try {
						while (true) {
							String string = bufferedReader.readLine();
							if(string == null) {
								System.out.println("---Disconnect---");
								break;
							}
							System.out.println(">" + string);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();

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
