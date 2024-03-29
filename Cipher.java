
import java.util.HashMap;

import java.util.*;

import java.lang.*;

public class Cipher{

	String keyword;
	Character [][] matrix=new Character[5][5];
	char[] keywordChar;
	ArrayList<Character> charList;
	Map<Character, Integer> map = new HashMap<>();
	
	public Cipher(String keyword){
		this.keyword=keyword;
		charList=new ArrayList<Character>();
		char[] alphabet=new char[]{'a','b','c','d','e','f','g','h','i','k','l',
		'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		keywordChar=keyword.toCharArray();
		for(char c:alphabet){
			 if(map.containsKey(c)) {
                int counter = map.get(c); //Throw exception here
                map.put(c, ++counter);
            } 
            else {
                map.put(c, 1);
            }
		}
	}

	public void KeywordCheck(){
		for(char c:keywordChar){
			 if(map.containsKey(c)) {
                int counter = map.get(c); //Throw exception here
                map.put(c, ++counter);
            } 
            else {
                map.put(c, 1);
            }
		}
	}

	public String[][] Generate(){
		KeywordCheck();
		int keywordCounter=0;
		int pointerI=0;
		int pointerJ=0;
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				if (keywordCounter>keywordChar.length-1){
					pointerI=i;
					pointerJ=j;
					break;
				}
				else{
					matrix[i][j]=keywordChar[keywordCounter];
					keywordCounter++;
				}
			}
			if (keywordCounter>keywordChar.length-1){
					break;
			}
		}

		 for(char c : map.keySet()) {
		 	//System.out.println(c);
            if(map.get(c) == 1) {
               matrix[pointerI][pointerJ]=c;
               pointerJ++;
               if(pointerJ==5){
               		pointerJ=0;
            		pointerI++;
            	}
            	// if(pointerI>4){

            	// 	break;

            	// }
            }
        }
		return null;
	}

	public String textCheck(String text){
		char[] textArray=text.toCharArray();
		int length=text.toCharArray().length;
		for(int i=0;i<length;i++){
			if(i!=0){
				if(textArray[i]==textArray[i-1]){
					charList.add('x');
					charList.add(textArray[i]);
				}
				else{
					charList.add(textArray[i]);
				}
			}
			else{
				charList.add(textArray[i]);
			}
		}
		if(charList.size()%2==1){
			charList.add('x');
		}
		StringBuilder sb= new StringBuilder();
		for(char c:charList){
			sb.append(c);
		}
		return sb.toString();
	}

	public String Algorithm(char f,char s){

		int placeFx=0;
		int placeFy=0;
		int placeSx=0;
		int placeSy=0;
		for(int i=0;i<matrix.length;i++){
			for(int j=0; j<matrix[0].length;j++){
				if(matrix[i][j]==f){
					placeFx=i;
					placeFy=j;
				}
				if(matrix[i][j]==s){
					placeSx=i;
					placeSy=j;
				}
			}
		}
		if(placeFx==placeSx){
			String temp;
			temp=Character.toString(matrix[placeFx][(placeFy+1)%5])+Character.toString(matrix[placeSx][(placeSy+1)%5]);
			return temp;
		}
		else if(placeFy==placeSy){
			String temp;
			temp=Character.toString(matrix[(placeFx+1)%5][placeFy])+Character.toString(matrix[(placeSx+1)%5][placeSy]);
			return temp;		
			}
		else{
			String temp;
			temp=Character.toString(matrix[placeFx][placeSy])+Character.toString(matrix[placeSx][placeFy]);
			return temp;
		}
	}
	public String toString(){
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
		return null;
	}
	public String encrypt(String input){
		input=textCheck(input);
		System.out.println(input);
		char[] slice=input.toCharArray();
		int counter=0;
		String temp="";
		while(counter<slice.length){
			temp=temp+ Algorithm(slice[counter],slice[counter+1]);
			counter+=2;
		}
		System.out.println(temp);
		return temp;
	}
	public String decipher(char f,char s){
		int placeFx=0;
		int placeFy=0;
		int placeSx=0;
		int placeSy=0;
		for(int i=0;i<matrix.length;i++){
			for(int j=0; j<matrix[0].length;j++){
				if(matrix[i][j]==f){
					placeFx=i;
					placeFy=j;
				}
				if(matrix[i][j]==s){
					placeSx=i;
					placeSy=j;
				}
			}
		}
		if(placeFx==placeSx){
			String temp;
			temp=Character.toString(matrix[placeFx][(placeFy-1)%5])+Character.toString(matrix[placeSx][(placeSy-11)%5]);
			return temp;
		}
		else if(placeFy==placeSy){
			String temp;
			temp=Character.toString(matrix[(placeFx-1)%5][placeFy])+Character.toString(matrix[(placeSx-1)%5][placeSy]);
			return temp;		
			}
		else{
			String temp;
			temp=Character.toString(matrix[placeFx][placeSy])+Character.toString(matrix[placeSx][placeFy]);
			return temp;
		}
	}
	public String decrypt(String input){
		char[] slice=input.toCharArray();
		int counter=0;
		String temp="";
		while(counter<slice.length){
			temp=temp+ decipher(slice[counter],slice[counter+1]);
			counter+=2;
		}
		System.out.println(temp);
		return temp;
	}
	public static void main(String[] args) {
		Cipher test=new Cipher("keyword");
		test.Generate();
		test.toString();
		test.encrypt("whattheheck");
		test.decrypt("yirvzugygyro");

	}
}
