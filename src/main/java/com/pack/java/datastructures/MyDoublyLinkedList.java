package com.pack.java.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyDoublyLinkedList<E> {

	private static class Node<E> {
		private E element;
		private Node<E> next;
		private Node<E> prev;

		private Node(E element, Node<E> next, Node<E> prev) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}

		private Node<E> getNext() {
			return next;
		}

		private void setNext(Node<E> next) {
			this.next = next;
		}
		
		private Node<E> getPrev() {
			return prev;
		}

		private void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		private void setElement(E element) {
			this.element = element;
		}

		public E getElement() {
			return this.element;
		}
	}

	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	public void addFirst(E element) {
		Node<E> newest = new Node<E>(element, null, null);
		
		if(size == 0) {
			tail = head = newest;
		}
		else {
//			newest.prev = head.prev;
			head.setPrev( newest );
			newest.setNext(head);
//			head.next = null;
			head = newest;
		}
		
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addLast(E element) {
		Node<E> newest = new Node<E>(element, null, null);
		
		if(isEmpty()) {
			head = newest;
		}
		else {
			tail.setNext(newest);
			newest.setPrev(tail);
		}
		tail = newest;
		size++;
		
	}
	
	public E removeFirst() {
		if(isEmpty()) {
			return null;
		}
		
		E element = head.getElement();
		head = head.getNext();
		head.setPrev(null);
		size--;
	
		if(size == 0) {
			tail = null;
		}
		
		return element;
	}
	
	public E removeLast() {
		if(isEmpty()) {
			return null;
		}
		
		E element = tail.getElement();
		tail = tail.getPrev();
		tail.setNext(null);
		size--;
		
		if(isEmpty()) {
			head = null;
		}
		
		return element;
	}

	public void listFront() {
		Node<E> temp = head;
		while (temp != null) {
			System.out.println(temp.getElement());
			temp = temp.getNext();
		}
	}
	
	public void listRear() {
		Node<E> temp = tail;
		while (temp != null) {
			System.out.println(temp.getElement());
			temp = temp.getPrev();
		}
	}

	public static void main(String[] args) {
		MyDoublyLinkedList<String> list = new MyDoublyLinkedList<>();
		list.addFirst("Narayan");
		list.addFirst("Adithya");
		list.addLast("AAR");
		list.addLast("Adams");
		
		list.removeFirst();
		list.removeLast();
		
		/*list.removeLast();
		list.removeLast();
		list.removeLast();
		list.removeLast();
		list.removeLast();*/
		
		list.listFront();
		list.listRear();
		
		list.addToList();
	}
	
	private void addToList() {
		List<Integer> list = new LinkedList<>();
		
		//Add to list
		
		iterateList(list);
	}
	
	private void iterateList(List<Integer> listToIterate) {
		ArrayList<Integer> list = (ArrayList<Integer>) listToIterate;
		
		//iterate list
	}
}