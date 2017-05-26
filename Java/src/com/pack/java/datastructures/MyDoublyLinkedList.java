package com.pack.java.datastructures;

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
			newest.prev = head.prev;
			head.prev = newest;
			newest.next = head;
			head.next = null;
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
		MyDoublyLinkedList<String> list = new MyDoublyLinkedList<>();
		list.addFirst("Narayan");
		list.addFirst("Adithya");
		list.addLast("AAR");
		list.addLast("Adams");
		
		/*list.removeLast();
		list.removeLast();
		list.removeLast();
		list.removeLast();
		list.removeLast();*/
		
		list.list();
	}
}
