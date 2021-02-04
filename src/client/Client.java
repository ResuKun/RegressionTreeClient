package client;

import java.io.IOException;
import java.net.Socket;
import java.rmi.ServerException;

import client.utility.io.Keyboard;

/**
 * Client per l'applicazione RegressionTree
 * 
 * @author Alessio Lorusso
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Communicator communicator = null;

		try {
			communicator = new Communicator(new Socket(args[0], new Integer(args[1]).intValue()));
			System.out.println("Connection to -> "+communicator.getSocketInfo());
		} catch (IOException e) {
			System.out.println(e.toString());
			return;
		}

		String answer = "";
		int decision = 0;
		do {
			System.out.println("Learn Regression Tree from database  [1]");
			System.out.println("Learn Regression Tree from data file [2]");
			System.out.println("Load Regression Tree from archive [3]");
			System.out.println("Close connection [4]");
			decision = Keyboard.readInt();
		} while (decision < 0 && decision > 4);

		String tableName = "";
		try {

			System.out.println("Starting data acquisition phase!");
			if (decision == 1) {
				communicator.sendIntegerValue(0);
				System.out.println("DB table source name: ");
				tableName = Keyboard.readString();
				communicator.sendMessage(tableName);
				System.out.println("Name an archive saving file: ");
				tableName = Keyboard.readString();
				communicator.sendMessage(tableName);
				answer = communicator.getMessage();
				if (!answer.equals("OK")) {
					System.out.println(answer);
					throw new ServerException("There's been a problem while reading from the db archive");
				}
			} else if (decision == 2){
				communicator.sendIntegerValue(1);
				System.out.println("Data file source name: ");
				tableName = Keyboard.readString();
				communicator.sendMessage(tableName);
				System.out.println("Name an archive saving file: ");
				tableName = Keyboard.readString();
				communicator.sendMessage(tableName);
				answer = communicator.getMessage();
				if (!answer.equals("OK")) {
					System.out.println(answer);
					throw new ServerException("There's been a problem while reading from a data file");
				}
			} else if (decision == 3){
				communicator.sendIntegerValue(2);
				System.out.println("Archive file name : ");
				tableName = Keyboard.readString();
				communicator.sendMessage(tableName);
				answer = communicator.getMessage();
				if (!answer.equals("OK")) {
					System.out.println(answer);
					throw new ServerException("There's been a problem while reading from an archive file");
				}
			}else if (decision == 4){
				communicator.sendIntegerValue(3);
				System.out.println("Connection closed succeffully");
			}

			if(decision != 4) {
				char risp = 'y';
				/*printTree*/
				answer = communicator.getMessage();
				System.out.println(answer+"\n");
				/*printRules*/
				answer = communicator.getMessage();
				System.out.println(answer+"\n");
				do {
					/*prediction phase*/
					System.out.println("Starting prediction phase! \n");
					answer = communicator.getMessage();
					while (answer.equals("QUERY")) {
						// Formualting query, reading answer
						answer = communicator.getMessage();
						System.out.println(answer);
						int path = Keyboard.readInt();
						communicator.sendIntegerValue(path);
						answer = communicator.getMessage();
					}
	
					if (answer.equals("OK")) { // Reading prediction
						Double value = (Double) communicator.getDoubleValue();
						System.out.println("Predicted class:" + value);
					} else // Printing error message
						System.out.println(answer);
	
					System.out.println("Would you repeat ? (y/n)");
					risp = Keyboard.readChar();
					communicator.sendCharValue(risp);
				} while (Character.toUpperCase(risp) == 'Y');
				
			}
		}catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				communicator.closeConnectionStreams();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
