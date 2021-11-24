package dsaa.lab02;

import java.util.*;






public class Main {

    static Scanner scan; // for input stream

    public static void main(String[] args) {

        System.out.println("START");
        scan=new Scanner(System.in);
        Document[] doc=null;
        int currentDocNo=0;
        int maxNo=-1;
        boolean halt=false;
        while(!halt) {
            String line=scan.nextLine();
            // empty line and comment line - read next line
            if(line.length()==0 || line.charAt(0)=='#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!"+line);
            String word[]=line.split(" ");
            // go n - start with array of the length n
            if(word[0].equalsIgnoreCase("go") && word.length==2) {
                maxNo=Integer.parseInt(word[1]);
                doc = new Document[maxNo];
                continue;
            }
            //ch - change index
            if(word[0].equalsIgnoreCase("ch") && word.length==2) {
                currentDocNo=Integer.parseInt(word[1]);
                continue;
            }

            // ld documentName
            if(word[0].equalsIgnoreCase("ld") && word.length==2) {
                doc[currentDocNo]=new Document(word[1],scan);
                continue;
            }
            // ha
            if(word[0].equalsIgnoreCase("ha") && word.length==1) {
                halt=true;
                continue;
            }
            // clear
            if(word[0].equalsIgnoreCase("clear") && word.length==1) {
                doc[currentDocNo].links.clear();
                continue;
            }
            // show
            if(word[0].equalsIgnoreCase("show") && word.length==1) {
                System.out.println(doc[currentDocNo].toString());
                continue;
            }
            // size
            if(word[0].equalsIgnoreCase("size") && word.length==1) {
                System.out.println(doc[currentDocNo].links.size());
                continue;
            }
            // add str
            if(word[0].equalsIgnoreCase("add") && word.length==2) {
                System.out.println(doc[currentDocNo].links.add(new Link(word[1])));
                continue;
            }
            // addi index str
            if(word[0].equalsIgnoreCase("addi") && word.length==3) {
                int index=Integer.parseInt(word[1]);
                try {
                    doc[currentDocNo].links.add(index, new Link(word[2]));
                }
                catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // get index
            if(word[0].equalsIgnoreCase("get") && word.length==2) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].links.get(index);
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // set index str
            if(word[0].equalsIgnoreCase("set") && word.length==3) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].links.set(index,new Link(word[2]));
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }

                continue;
            }
            // index str
            if(word[0].equalsIgnoreCase("index") && word.length==2) {
                int index=doc[currentDocNo].links.indexOf(new Link(word[1]));
                System.out.println(index);
                continue;
            }
            // remi index
            if(word[0].equalsIgnoreCase("remi") && word.length==2) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].links.remove(index);
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // rem str
            if(word[0].equalsIgnoreCase("rem") && word.length==2) {
                System.out.println(doc[currentDocNo].links.remove(new Link(word[1])));
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }
}

/*
go 10
ld doc1
link=a and link=b
write also link=c end finish.
eod
show
add d
show
rem a
remi 2
show
get 0
set 1 w
show
ch 1
ld doc2
eod
show
remi 0
index x
ha

START
2	!go 10
3	!ld doc1
4	!index w
5	-1
6	!get 0
7	error
8	!addi 0 a
9	error !addi 1 b
10	!addi 1 b 2 c
11	error !addi 4 w
12	!addi 2 c error
13	error !show
14	!addi 4 w Document: doc1
15	error a
16	!show b
17	Document: doc1 c
18	!addi 0 x
19	error !show
20	!show Document: doc1
21	Document: doc1 x
22	!get 0 a
23	error b
24	!get 1 c
25	error !get 0
26	!get 2 x
27	error !get 1
28	!get 3 a
29	error !get 2
30	!rem x b
31	false !get 3
32	!show c
33	Document: doc1 !rem x
34	!rem x true
35	false !show
36	!index x Document: doc1
37	-1 a
38	!show b
39	Document: doc1 c
40	!rem c x
41	false
42	!show !index x
43	Document: doc1 -1
44	!rem a !show
45	false Document: doc1
46	!rem b a
47	false b
48	!show c
49	Document: doc1 !rem c
50	!rem x true
51	false !show
52	!add a Document: doc1
53	true a
54	!add b
55	true !rem a
56	!add c true
57	true !rem b
58	!remi 3 true
59	error !show
60	!show Document: doc1
61


 */