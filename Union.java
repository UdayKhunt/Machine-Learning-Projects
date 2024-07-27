import java.util.Arrays;

public class Union {
    int[] parent;
    int[] rank;
    int[] size;

    Union(int n){
        parent = new int[n + 1];
        rank = new int[n + 1];
        size = new int[n + 1];
        Arrays.fill(size , 1);

        for(int i = 0 ; i < n ; ++i){
            parent[i] = i;
        }
    }

    int findUPar(int x){
        if (parent[x] == x){
            return x;
        }
        return parent[x] = findUPar(parent[x]);
    }

    void unionByRank(int x , int y){
        int ulp_x = findUPar(x);
        int ulp_y = findUPar(y);

        if (ulp_y == ulp_x) return;

        if (rank[ulp_x] < rank[ulp_y]) parent[ulp_x] = ulp_y;
        else if (rank[ulp_y] < rank[ulp_x]) parent[ulp_y] = ulp_x;
        else{
            parent[ulp_x] = ulp_y;
            rank[ulp_y]++;
        }
    }

    void unionBySize(int x , int y){
        int ulp_x = findUPar(x);
        int ulp_y = findUPar(y);

        if (ulp_y == ulp_x) return;

        if (size[ulp_x] < size[ulp_y]){
            parent[ulp_x] = ulp_y;
            size[ulp_y] += size[ulp_x];
        }
        else{
            parent[ulp_y] = ulp_x;
            size[ulp_x] += size[ulp_y];
        }
    }

    public static void main(String[] args) {
        Union ds = new Union(7);
        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        if (ds.findUPar(3) == ds.findUPar(7)) {
            System.out.println("Same");
        } else
            System.out.println("Not Same");

        ds.unionBySize(3, 7);
        if (ds.findUPar(3) == ds.findUPar(7)) {
            System.out.println("Same");
        } else
            System.out.println("Not Same");
    }
}
