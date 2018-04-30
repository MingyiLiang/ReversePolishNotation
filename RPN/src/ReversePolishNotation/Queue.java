package ReversePolishNotation;


/**
 * build a queue
 * @author mingyiliang
 *
 */
public class Queue {
	private Object[] data;
	
	private int front ;
	
	private int rear ;
	
	private boolean isEmpty;
	
	public Queue() {
        data = new Object[262143];
        front = rear = 0;
        isEmpty = true;
    }
	
	public boolean isEmpty() {
//		if (front == rear) {
//			return true;
//		}else {
//			return false;
//		}
		return this.isEmpty;
	}
	
	public boolean isFull() {
		int nextVal = rear;
		if (nextVal == data.length - 1) {
			nextVal = 0;
		}else {
			nextVal = nextVal + 1;
		}
		if (front == nextVal) {
			return true;
		}else {
			return false;
		}
	}
	
	public void enQueue(Object element) {
//		if(isFull()) {
//			Object[] newArray = new Object[2 * data.length];
//			int cursor = 0;
//			while(front != rear) {
//				newArray[cursor] = data[front];
//				front = (front+1) % data.length;
//				cursor = cursor +1;
//			}
//			newArray[cursor] = data[rear];
//			front = 0;
//			rear = cursor;
//			data = newArray;
//			rear = (rear+1) % data.length;
//			data[rear] = element;
//			
//		}else {
//			if(isEmpty()) {
//				front = rear = 0;
//				data[rear] = element;				
//			}else {
//				rear = (rear+1) % data.length;
//				data[rear] = element;
//			}
//		}
		
		
		if (isFull()) {
            System.out.println("Queue is full");
            return;
        }

        // Set the data to the location where rear is referring to
        data[rear] = element;

        // If front was also referring to the same location, it implies queue was empty earlier. 
        // So isEmpty flag has to be set to false
        if (front == rear) {
            isEmpty = false;
        }

        // Increment rear after enqueing the data
        if (rear == data.length - 1) {
            rear = 0;
        } else {
            rear++;
        }
    }

	
	
	public Object deQueue() {
		// If queue is empty, return null
        if (isEmpty) {
            System.out.println("Queue is already empty");
            return null;
        }

        Object element = data[front];

        // Move the start pointer accordingly
        if (front == data.length - 1) {
            front = 0;
        } else {
            front++;
        }

        
        if (front == rear) {
            isEmpty = true;
        }

        return element;
	}
	
	public Object getFront() {
		if (isEmpty) {
            return null;
        }

        return data[front];
	}
	
	
	/**
	 * Returns a String representation of the current queue
	 */
	public String toString(){
		int iterate = front;
        StringBuilder contents = new StringBuilder();
        contents.append("contents : ");

        if (isEmpty) {
            contents.append("none");
            return contents.toString();
        }
        while (iterate == rear) {
            contents.append(data[iterate].toString()).append(", ");
            if (iterate == data.length - 1) {
                iterate = 0;
            } else {
                iterate++;
            }
        }

        return contents.toString();
	       
    }
	
}
