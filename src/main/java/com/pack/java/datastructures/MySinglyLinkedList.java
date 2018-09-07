package com.pack.java.datastructures;

public class MySinglyLinkedList<E> {

	private static class Node<E> {
		private E element;
		private Node<E> next;

		private Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public E getElement() {
			return this.element;
		}
	}

	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	public void addFirst(E element) {
		head = new Node<E>(element, head);
		
		if(size == 0) {
			tail = head;
		}
		
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addLast(E element) {
		Node<E> newest = new Node<E>(element, null);
		
		if(isEmpty()) {
			head = newest;
		}
		else {
			tail.setNext(newest);
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
		tail = tail.getNext();
		size--;
		
		if(isEmpty()) {
			head = null;
		}
		
		return element;
	}

	public void list() {
		Node<E> temp = head;
		while (temp != null) {
			System.out.println(temp.getElement());
			temp = temp.getNext();
		}
	}

	public static void main(String[] args) {
		MySinglyLinkedList<String> list = new MySinglyLinkedList<>();
		list.addFirst("Narayan");
		list.addFirst("Adithya");
		list.addLast("AAR");
		list.addLast("Adams");
		
		list.removeLast();
		list.removeLast();
		list.removeLast();
		list.removeLast();
		list.removeLast();
		
		list.list();
	}
}
