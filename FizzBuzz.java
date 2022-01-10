package ProblemStatementV;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.IntConsumer;

class PrintFizz implements Runnable {
//printFizz that prints the word "Fizz" to the console,5
	public void printFizz(){
		System.out.println("Fizz");
	}
	
	@Override
	public void run() {
		printFizz();
	}
}

class PrintBuzz implements Runnable {
	// printBuzz that prints the word "Buzz" to the console,
	public void printBuzz(){
		System.out.println("Buzz");
	}

	@Override
	public void run() {
		printBuzz();
		
	}
}

class PrintFizzBuzz implements Runnable {
	// printFizzBuzz that prints the word "FizzBuzz" to the console, and
	public void printFizzBuzz(){
		System.out.println("FizzBuzz");
	}

	@Override
	public void run() {
		printFizzBuzz();
		
	}
}

class FizzBuzz {
	private int n;
	private volatile int i=1;
	private volatile Object lock = new Object();
	
	public FizzBuzz(int n) {
		this.n = n;
	}
	// printFizz.run() outputs "fizz".
	public void fizz(Runnable printFizz) throws InterruptedException {
		synchronized (lock){
			for ( ; i<= n; ){
				if (i % 3 == 0 && i % 5 != 0){
					new PrintFizz().printFizz();
					i++;
				}else {
					lock.notifyAll();
					lock.wait();
				}
			}
		}
	}
	// printBuzz.run() outputs "buzz".
	public void buzz(Runnable printBuzz) throws InterruptedException {
		synchronized (lock){
			for (; i<= n; ){
				if (i%3 != 0 && i % 5 == 0 ){
					new PrintBuzz().printBuzz();
					i++;
				}else {
					lock.notifyAll();
					lock.wait();
				}
			}
		}
	}
	// printFizzBuzz.run() outputs "fizzbuzz".
	public void fizzbuzz(Runnable printFizzBuzz) throws
	InterruptedException {
		synchronized (lock){
			for (; i<= n; ){
				if(i % 3 == 0 && i % 5 == 0)
				{
					new PrintFizzBuzz().printFizzBuzz();
					i++;
				}else {
					lock.notifyAll();
					lock.wait();
				}
			}
		}
	}
	// printNumber.accept(x) outputs "x", where x is an integer.
	public void number(IntConsumer printNumber) throws
	InterruptedException {
		
		synchronized (lock){
			for (; i<= n;){
				if(i % 3 != 0 && i % 5 != 0)
				{
					printNumber = (x)->System.out.println(x);
					printNumber.accept(i);
					i++;
				}else {
					lock.notifyAll();
					lock.wait();
				}
			}
		}
	}

	public static void main(String[] args){
		try{
			int nValue = 0;
			Scanner scan = new Scanner(System.in);
			nValue= scan.nextInt();
			scan.close();
			if (nValue < 1 || nValue > 50){
				throw new IOException("Value has to be <= 50 and >= 1");
			}
			
			FizzBuzz objFizzBuzz = new FizzBuzz(nValue);
			Thread threadA =new Thread(() -> {
				try {
					objFizzBuzz.number(null);
	            } catch (InterruptedException e) {
	            	System.out.println(e.getLocalizedMessage());
	            }
			}); 
			Thread threadB =new Thread(() -> {
				try {
					objFizzBuzz.buzz(null);
	            } catch (InterruptedException e) {
	            	System.out.println(e.getLocalizedMessage());
	            }
			}); 
			Thread threadC =new Thread(() -> {
				try {
					objFizzBuzz.fizz(null);
	            } catch (InterruptedException e) {
	            	System.out.println(e.getLocalizedMessage());
	            }
			}); 
			Thread threadD =new Thread(() -> {
				try {
					objFizzBuzz.fizzbuzz(null);
	            } catch (InterruptedException e) {
	            	System.out.println(e.getLocalizedMessage());
	            }
			}); 
			
			threadA.start();
			threadB.start();
			threadC.start();
			threadD.start();

		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}


