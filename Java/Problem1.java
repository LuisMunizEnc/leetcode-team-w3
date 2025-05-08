class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;
        int[] res = new int[n];
        int i = 0, j = 0, k = 0;

        while(k < n){
            if(i < n1){
                if(j < n2){
                    if(nums1[i] <nums2[j]) 
                        res[k++] = nums1[i++];
                    else res[k++] = nums2[j++];
                }else res[k++] = nums1[i++];
            }
            else res[k++] = nums2[j++];
        }

        int mid = (n1+n2)/2 - 1;

        if((n1+n2)%2 == 0){
            return (res[mid]+res[mid+1])/(double) 2;
        }else{
            return (double) res[mid+1];
        }
    }
}