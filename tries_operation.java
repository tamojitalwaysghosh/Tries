public class HelloWorld {
    static class Node {
        Node[] children;
        boolean eow;  // end of word
        
        Node() {
            // Initialize the children array with size 26 (for lowercase letters)
            this.children = new Node[26];
            this.eow = false;
        }
    }
    
    public static void insertKey(Node root, String str) {
        Node curr = root;
        
        for(char c : str.toCharArray()) {
            if(curr.children[c-'a'] == null) {  // Fixed condition: insert if null
                curr.children[c-'a'] = new Node();  // Create new node correctly
            }
            
            curr = curr.children[c-'a'];
        }
        
        curr.eow = true;
    }
    
    public static boolean searchKey(Node root, String str) {
        Node curr = root;
        
        for(char c : str.toCharArray()) {
            if(curr.children[c-'a'] == null) {
                return false;
            }
            
            curr = curr.children[c-'a'];
        }
        
        return curr.eow;
    }
    
    public static void print(Node curr, StringBuilder sb) {
        if(curr.eow) {
            System.out.println(sb.toString());
        }
        
        for(int i = 0; i < 26; i++) {
            if(curr.children[i] != null) {
                char c = (char)('a' + i);
                sb.append(c);
                print(curr.children[i], sb);
                sb.setLength(sb.length() - 1);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("================Tries Example===============");
        
        Node root = new Node();
        
        String[] words = {
            "the", "a", "there", "their", "any"
        };
        
        for(String word : words) {
            insertKey(root, word);
        }
        
        StringBuilder sb = new StringBuilder();
        print(root, sb);
        
        // Test searching
        System.out.println("\nSearching for words:");
        System.out.println("'the': " + searchKey(root, "the"));
        System.out.println("'their': " + searchKey(root, "their"));
        System.out.println("'thor': " + searchKey(root, "thor"));
    }
}
