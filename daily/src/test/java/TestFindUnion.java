import com.wcq.unionFind.Friend;
import com.wcq.unionFind.ILinkPoint;
import com.wcq.unionFind.weightedQuickUnion.WeightedQuickUinon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFindUnion {
    public static void main(String[] args) {
        List<ILinkPoint> list = new ArrayList<>();
        for(int i=0; i<100; i++){
            Friend friend = new Friend(i);
            list.add(friend);
        }


        Random random = new Random();

        WeightedQuickUinon qu = new WeightedQuickUinon(list);
//        for(int i=0; i<80; i++){
//            int index1 = random.nextInt(100);
//            int index2 = random.nextInt(100);
//            qu.union(index1, index2);
//        }
        qu.union(1, 2);
        qu.union(2, 8);

        System.out.println(qu.connected(1, 8));
        System.out.println(qu.connected(2, 8));
        System.out.println(qu.connected(3, 8));

        System.out.println(qu.getCount());

    }
}
