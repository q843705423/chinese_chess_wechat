package io.github.q843705423.util;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.BoardObject;

import java.util.List;

public class Node {
    /**
     * 总访问次数
     */
    public static int allTime = 0;

    /**
     * 蒙特卡洛常数
     */
    public static int c = 3;
    public static volatile boolean timeOut = false;
    /**
     * 子节点
     */
    private Node[] child;
    /**
     * 子节点长度
     */
    private int childLen;

    /**
     * 局面字符串
     */
    private String fen;


    /**
     * 着法名
     */
    private String zhaoName;

    public String getZhaoName() {
        return zhaoName;
    }

    /**
     * 得分
     */
    private int t;

    /**
     * 节点访问次数
     */
    private int n;

    public Node(String fen) {
        this.fen = fen;
    }

    public int playOut(String fen) {
        Protocol protocol = new Protocol(fen);
        Board.init(protocol);
        BoardObject boardObject = new BoardObject(protocol);
        while (true) {
            boardObject.randomMove();
            boolean b = boardObject.gameIsOver();
            if (b) {
                break;
            }
        }
        return boardObject.winOrLose();
    }

    public void searchMore(int time) {
        for (int i = 0; i < time; i++) {
            this.search(this);
        }
    }

    public int search(Node node) {
        //是否是叶子节点
        if (isTheLeaf(node)) {
            if (node.n == 0) {
                node.n++;
                t = playOut(node.fen);
                allTime++;
                return t;
            } else {
                node.n++;
                generateAllChild(node);
                return 0;
            }

        } else {
            Node sub = chooseGoodNode(node);
            int search = search(sub);
            //反向传播
            node.t += search;
            node.n++;
            return search;
        }
    }

    /**
     * 选择ucb值最大的子节点
     *
     * @param node node
     */
    public Node chooseGoodNode(Node node) {
        double z = -10000_0000;
        int index = -1;
        for (int i = 0; i < node.childLen; i++) {
            double v = calculateUCB(node.child[i]);
            if (v > z) {
                z = v;
                index = i;
            }
        }
        return index == -1 ? null : node.child[index];

    }

    /**
     * 生成所有子节点
     *
     * @param node node
     */
    private void generateAllChild(Node node) {
        Protocol protocol = new Protocol(fen);
        BoardObject boardObject = new BoardObject(protocol);
        List<int[]> maybeList = boardObject.getMaybeList();
        node.child = new Node[maybeList.size()];

        boardObject.protocol.changeColor();
        for (int i = 0; i < maybeList.size(); i++) {
            int[] whoToWhere = maybeList.get(i);
            boardObject.move(whoToWhere);

            node.child[i] = new Node(boardObject.getFen());
            node.childLen = maybeList.size();
            boardObject.unMove();
            node.child[i].zhaoName = boardObject.zhao(whoToWhere[0], whoToWhere[1]);
        }

    }

    private boolean isTheLeaf(Node node) {
        return node.child == null;
    }

    public double calculateV(Node node) {
        int sum = 0;
        if (node.childLen == 0) {
            return node.t;
        }
        for (int i = 0; i < node.childLen; i++) {
            sum += node.child[i].t;

        }
        return sum * 1.0 / node.childLen;

    }

    public double calculateUCB(Node node) {
        return node.n == 0 ? Double.MAX_VALUE : calculateV(node) + c * Math.sqrt(Math.log(allTime) / node.n);
    }

}
