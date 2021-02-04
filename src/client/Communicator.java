package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * Classe atta allo scambio di informazioni con l'esterno
 * 
 * @author Alessio Lorusso
 *
 */
public class Communicator {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	/**
	 * Costruttore che accetta una {@link Socket} inizializzare gli stream
	 * @param socket
	 * @throws IOException
	 */
	public Communicator(Socket socket) throws IOException {
		this.socket = socket;
		out = new ObjectOutputStream(this.socket.getOutputStream());
		in = new ObjectInputStream(this.socket.getInputStream());
	}

	/**
	 * Invia una {@link String} attraverso l'output stream
	 * 
	 * @param {@link String} msg
	 * @throws IOException
	 */
	public void sendMessage(String msg) throws IOException {
		try {
			out.writeObject(msg);
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> sendMessage : " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Riceve una {@link String} attraverso l'input stream
	 * 
	 * @return {@link String}
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String getMessage() throws ClassNotFoundException, IOException {
		String result = null;
		try {
			result = (String) in.readObject();
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> getMessage : " + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException >>> Communicator >>> getMessage : " + e.getMessage());
			throw e;
		}
		return result;
	}

	/**
	 * Invia un double attraverso l'output stream
	 * 
	 * @param double value
	 * @throws IOException
	 */
	public void sendDoubleValue(double value) throws IOException{
		try {
			out.writeObject(value);
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> sendDoubleValue : " + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Riceve un {@link Double}  dall'input stream
	 * 
	 * @return {@link Double}
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Double getDoubleValue() throws ClassNotFoundException, IOException {
		Double result = null;
		try {
			result = (Double) in.readObject();
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> getDoubleValue : " + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException >>> Communicator >>> getDoubleValue : " + e.getMessage());
			throw e;
		}
		return result;
	}
	
	/**
	 * Invia un int attraverso l'output stream
	 * 
	 * @param int} value
	 * @throws IOException
	 */
	public void sendIntegerValue(int value) throws IOException {
		try {
			out.writeObject(value);
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> sendIntegerValue : " + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Riceve un {@link Integer} dall'input stream
	 * 
	 * @return {@link Integer}
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Integer getIntegerValue() throws ClassNotFoundException, IOException {
		Integer result = null;
		try {
			result = (Integer) in.readObject();
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> getIntegerValue : " + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException >>> Communicator >>> getIntegerValue : " + e.getMessage());
			throw e;
		}
		return result;
	}

	/**
	 * Invia un char attraverso l'output stream
	 * 
	 * @param int} value
	 * @throws IOException
	 */
	public void sendCharValue(char value) throws IOException {
		try {
			out.writeObject(value);
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> sendCharValue : " + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Riceve un {@link Character} dall'input stream
	 * 
	 * @return {@link Character}
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Character getCharValue() throws ClassNotFoundException {
		Character result = null;
		try {
			result = (Character) in.readObject();
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> getCharValue : " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException >>> Communicator >>> getCharValue : " + e.getMessage());
			throw e;
		}
		return result;
	}
	
	
	/**
	 * Chiude gli stream
	 * 
	 * @throws IOException
	 */
	public void closeConnectionStreams() throws IOException {
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> closeConnectionStreams : " + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Chiude la socket
	 * 
	 * @throws IOException
	 */
	public void closeSocket() throws IOException {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("IOException >>> Communicator >>> closeSocket : " + e.getMessage());
			throw e;
		}
	}

	/**
	 * True se la socket è chiusa
	 * 
	 * @return
	 */
	public boolean isSocketClosed() {
		return socket.isClosed();
	}

	
	/**
	 * Resituisce una {@link String} con le informazioni
	 * della connessione aperta
	 * 
	 * @return {@link String}
	 */
	public String getSocketInfo() {
		return socket.toString();
	}
	
}
