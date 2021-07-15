package com.jshop;
class Gene{
    private int index;
    public int cnt;
    private char[] gene;
    private char gene_base;

    public Gene(){}
    public Gene(String a){
        cnt = 0;
        index = 0;
        gene = a.toCharArray();
        gene_base = ' ';
    }
    public void updateGene(){
        cnt = 0;
        gene_base = ' ';
        if(index >= gene.length){
            return;
        }


        while(index < gene.length && gene_base == ' '){
            if(gene[index] >= '0' && gene[index] <= '9'){
                cnt = cnt * 10 + (gene[index] - '0');
            }


            else{
                gene_base = gene[index];
            }
            index ++;
        }
    }
    public char get_base(){
        return gene_base;
    }
}
public class Solution {

    public static void main(String[] args){
        Solution s = new Solution();
     String sd=   Solution.GeneSimilarity("9489T174A154C165G2A1G1T1C1G1T1A1C1G1A1G1A1T1C1T2A","1102G8816C19T6A34T1C10T2G2A2T1G1C1A1C2T");
   System.out.println(sd);

    }


    public static String GeneSimilarity(String Gene1, String Gene2) {
        // write your code here
        int same_time = 0, cnt_time = 0;


        Gene g1 = new Gene(Gene1);
        Gene g2 = new Gene(Gene2);


        g1.updateGene();
        g2.updateGene();


        cnt_time += g1.cnt;
        while (g1.get_base() != ' ' && g2.get_base() != ' ') {
            if (g1.get_base() == g2.get_base()) {
                same_time += Math.min(g1.cnt, g2.cnt);
            }


            if (g1.cnt > g2.cnt) {
                g1.cnt -= g2.cnt;
                g2.updateGene();
            } else if (g1.cnt < g2.cnt) {
                g2.cnt -= g1.cnt;
                g1.updateGene();
                cnt_time += g1.cnt;
            } else {
                g1.updateGene();
                g2.updateGene();
                cnt_time += g1.cnt;
            }
        }
        return String.valueOf(same_time) + "/" + String.valueOf(cnt_time);
    }
}
