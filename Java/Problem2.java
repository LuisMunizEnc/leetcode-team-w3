class LRUCache {
    private Map<Integer, Integer> cache = new HashMap<>();
    private Map<Integer, Integer> use = new HashMap<>();
    private int limit;
    private int priority = 0;
    private PriorityQueue<Integer> minPriorityKeys;

    public LRUCache(int capacity) {
        this.limit = capacity;
        this.minPriorityKeys = new PriorityQueue<>((k1,k2) -> 
            Integer.compare(use.get(k1), use.get(k2))
        );
    }
    
    public int get(int key) {
        if(use.containsKey(key)){
            use.put(key,priority++);
            minPriorityKeys.remove(key);
            minPriorityKeys.offer(key);
            return cache.get(key);
        }else{
            System.out.println(key + " Not found!");
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            cache.put(key, value);
            use.put(key, priority++);
            minPriorityKeys.remove(key);
            minPriorityKeys.offer(key);
        }else if(cache.size() == limit){
            Integer lruKey = minPriorityKeys.poll();
            if(lruKey != null){
                cache.remove(lruKey);
                use.remove(lruKey);
            }
            cache.put(key,value);
            use.put(key, priority++);
            minPriorityKeys.offer(key);
        }else{
            cache.put(key, value);
            use.put(key, priority++);
            minPriorityKeys.offer(key);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */