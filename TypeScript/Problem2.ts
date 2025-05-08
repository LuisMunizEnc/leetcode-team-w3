class ListNode {
    key: number;
    value: number;
    prev: ListNode | null = null;
    next: ListNode | null = null;

    constructor(key: number, value: number) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    private capacity: number;
    private cache: Map<number, ListNode>;
    private head: ListNode;
    private tail: ListNode;

    constructor(capacity: number) {
        this.capacity = capacity;
        this.cache = new Map();

        this.head = new ListNode(0, 0);
        this.tail = new ListNode(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    private remove(node: ListNode): void {
        const prev = node.prev!;
        const next = node.next!;
        prev.next = next;
        next.prev = prev;
    }

    private addToFront(node: ListNode): void {
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next!.prev = node;
        this.head.next = node;
    }

    get(key: number): number {
        if (!this.cache.has(key)) return -1;

        const node = this.cache.get(key)!;
        this.remove(node);
        this.addToFront(node);
        return node.value;
    }

    put(key: number, value: number): void {
        if (this.cache.has(key)) {
            const node = this.cache.get(key)!;
            node.value = value;
            this.remove(node);
            this.addToFront(node);
        } else {
            const newNode = new ListNode(key, value);
            this.cache.set(key, newNode);
            this.addToFront(newNode);

            if (this.cache.size > this.capacity) {
                const lru = this.tail.prev!;
                this.remove(lru);
                this.cache.delete(lru.key);
            }
        }
    }
}
