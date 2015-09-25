import java.io.*;
import java.util.*;
public class waterFlow {
    public static void main(String[]args)throws IOException
    {
        try
        {
            FileInputStream fstream=new FileInputStream(args[1]);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            //output file
            BufferedWriter opt=null;
            File file=new File("output.txt");
            opt = new BufferedWriter(new FileWriter(file));
            
            String str=br.readLine();
            int n=Integer.parseInt(str);
            for (int i=1;i<=n;i++)
            {
                str=br.readLine();
                //System.out.println(str+" ITERATION : "+i);
                switch(str)
                {
                    case "BFS":
                    {
                        String source=br.readLine();
                        
                        String d=br.readLine();
                        String dest[]=d.split("\\s+");
                        
                        String m=br.readLine();
                        String middle_nodes[]=m.split("\\s+");
                        
                        int no_pipe=Integer.parseInt(br.readLine());
                        
                        str=br.readLine();
                        HashMap<String,ArrayList<String>> adj = new HashMap<>();
                        for(int pipes=1;pipes<=no_pipe;pipes++)
                        {
                            String[]splited=str.split("\\s+");
                            if(adj.get(splited[0])==null)
                            {
                                ArrayList<String> a=new ArrayList<String>();
                                a.add(splited[1]);
                                adj.put(splited[0],a);
                            }
                            else
                                adj.get(splited[0]).add(splited[1]);
                            str=br.readLine();
                        }
                        //Iterator iterator = adj.keySet().iterator();
                        /*while (iterator.hasNext()) 
                        {
                            String key = (String)iterator.next();
                            System.out.println(key);
                        }*/
                        int start_time=Integer.parseInt(str);
                        HashMap<String,Integer> time=new HashMap();
                        time.put(source,start_time);
                        for(int j=0;j<dest.length;j++)
                            time.put(dest[j],0);
                        for(int j=0;j<middle_nodes.length;j++)
                            time.put(middle_nodes[j],0);
                        
                        int success=0;
                        LinkedList<String> ll = new LinkedList<String>();
                        ll.add(source);
                        String temp="";
                        boolean flag;
                        int size=0;
                        while(success==0 && ll.size()!=0)
                        {
                            flag=false;
                            //
                            
                            for(int x=1;x<=ll.size() && !flag;x++)
                            {
                                String s=ll.removeFirst();
                                //System.out.println(s);
                                if(adj.get(s)!=null)
                                {
                                    ArrayList<String> insert=adj.get(s);
                                    if(insert.size()>1)
                                        Collections.sort(insert);
                                    for(int y=0;y<insert.size();y++)
                                    {
                                        if(ll.indexOf(insert.get(y))==-1 && time.get(insert.get(y))==0)
                                        {
                                            time.put(insert.get(y), time.get(s)+1);
                                            int found=0;
                                            for(;found<dest.length;found++)
                                                if(insert.get(y).equals(dest[found]))
                                                {
                                                    success=1;
                                                    flag=true;
                                                    temp=insert.get(y);
                                                    break;
                                                }
                                            if(!flag)
                                                ll.add(insert.get(y));
                                            else
                                                break;
                                        }
                                    }
                                }  
                            }
                            
                        }
                        if(success!=0)
                        {
                            //System.out.println(temp+" "+time.get(temp)%24);
                            opt.write(temp+" "+Integer.toString(time.get(temp)%24));
                            opt.newLine();
                        }
                        else
                        {
                            opt.write("None");
                            opt.newLine();
                        }
                        
                        str=br.readLine();
                    }break;
                    case "DFS":
                    {
                        String source=br.readLine();
                        
                        String d=br.readLine();
                        String dest[]=d.split("\\s+");
                        
                        String m=br.readLine();
                        String middle_nodes[]=m.split("\\s+");
                        
                        int no_pipe=Integer.parseInt(br.readLine());
                        
                        str=br.readLine();
                        HashMap<String,ArrayList<String>> adj = new HashMap<>();
                        for(int pipes=1;pipes<=no_pipe;pipes++)
                        {
                            String[]splited=str.split("\\s+");
                            if(adj.get(splited[0])==null)
                            {
                                ArrayList<String> a=new ArrayList<String>();
                                a.add(splited[1]);
                                adj.put(splited[0],a);
                            }
                            else
                                adj.get(splited[0]).add(splited[1]);
                            str=br.readLine();
                        }
                        //Iterator iterator = adj.keySet().iterator();
                        /*while (iterator.hasNext()) 
                        {
                            String key = (String)iterator.next();
                            System.out.println(key);
                        }*/
                        int start_time=Integer.parseInt(str);
                        
                        HashMap<String,Integer> time=new HashMap();
                        //HashMap<String,Integer> visited=new HashMap<String,Integer>();
                        
                        time.put(source,start_time);
                        //visited.put(source,1);
                        
                        for(int j=0;j<dest.length;j++)
                        {
                            time.put(dest[j],0);
                            //visited.put(dest[j],0);
                        }
                        for(int j=0;j<middle_nodes.length;j++)
                        {
                            time.put(middle_nodes[j],0);
                            //visited.put(middle_nodes[j],0);
                        }
                        int success=0;
                        LinkedList<String> ll = new LinkedList<String>();
                        ll.add(source);
                        String temp="";
                        boolean flag;
                        while(success==0 && ll.size()!=0)
                        {
                            int k;
                            String rem=ll.getLast();
                            ArrayList<String> check=new ArrayList<String>();
                            if(adj.get(rem)!=null)
                            {
                                check=adj.get(rem);
                                if(check.size()>1)
                                    Collections.sort(adj.get(rem));
                                for(k=0;k<check.size();k++)
                                    if(time.get(check.get(k))==0)
                                    {
                                        time.put(check.get(k),time.get(rem)+1);
                                        //visited.put(check.get(k),1);
                                        //check if it is a destination node
                                        int found=0;
                                        for(;found<dest.length;found++)
                                            if(check.get(k).equals(dest[found]))
                                            {
                                                temp=check.get(k);
                                                success=1;
                                                break;
                                            }
                                        ll.addLast(check.get(k));
                                        break;
                                    }
                                if(k==check.size())
                                    ll.removeLast();
                            } 
                            else
                                ll.removeLast();
                        }
                        if(success!=0)
                        {
                            //System.out.println(temp+" "+time.get(temp)%24);
                            opt.write(temp+" "+Integer.toString(time.get(temp)%24));
                            opt.newLine();
                        }
                        else
                        {
                            opt.write("None");
                            opt.newLine();
                        }
                        str=br.readLine();
                    }break;
                    case "UCS":
                    {
                        String source=br.readLine();
                        
                        String d=br.readLine();
                        String dest[]=d.split("\\s+");
                        
                        String m=br.readLine();
                        String middle_nodes[]=m.split("\\s+");
                        
                        int no_pipe=Integer.parseInt(br.readLine());
                        
                        str=br.readLine();
                        HashMap<String,ArrayList<String>> adj = new HashMap<>();
                        HashMap<String,ArrayList<Integer>> interval = new HashMap<>();
                        for(int pipes=1;pipes<=no_pipe;pipes++)
                        {
                            String[]splited=str.split("\\s+");
                            if(adj.get(splited[0])==null)
                            {
                                ArrayList<String> a=new ArrayList<String>();
                                a.add(splited[1]);
                                adj.put(splited[0],a);
                            }
                            else
                                adj.get(splited[0]).add(splited[1]);
                           
                            
                            //interval hashmap
                            
                            int times=Integer.parseInt(splited[3]);
                            ArrayList<Integer> insert=new ArrayList();
                            //insert.add(Integer.parseInt(splited[2]));
                            for(int x=0;x<times;x++)
                            {
                                String st[]=splited[x+4].split("-");
                                int t1=Integer.parseInt(st[0]);
                                int t2=Integer.parseInt(st[1]);
                                //System.out.println(t1+" "+t2);
                                for(int j=t1;j<=t2;j++)
                                {
                                    int o=j;
                                    if(o>23)
                                        o-=24;
                                    if(insert.indexOf(o)<0)
                                        insert.add(o);
                                }
                            }
                            insert.add(0,Integer.parseInt(splited[2]));
                            interval.put(splited[0]+" "+splited[1],insert);
                            //System.out.println(str);
                            //System.out.println(insert);
                            str=br.readLine();
                        }
                        
                        int start_time=Integer.parseInt(str);
                        //System.out.println(start_time);
                        HashMap<String,int[]> time=new HashMap();
                        int temp[]={start_time,start_time};
                        time.put(source,temp);
                        
                        int temp1[]={0,0};
                        for(int j=0;j<dest.length;j++)
                            time.put(dest[j],temp1);
                        for(int j=0;j<middle_nodes.length;j++)
                            time.put(middle_nodes[j],temp1);
                        
                        LinkedList<String> open=new LinkedList();
                        LinkedList<Integer> open_time=new LinkedList();
                        LinkedList<String> closed=new LinkedList();
                        open.add(source);
                        open_time.add(start_time);
                        String result="";
                        int units=0;
                        while(open.size()!=0)
                        {
                            //System.out.println(open);
                            //System.out.println(open_time);
                            //System.out.println("closed: "+closed);
                            String rem=open.removeFirst();
                            units=open_time.removeFirst();
                            int got=0;
                            for(got=0;got<dest.length;got++)
                                if(rem.equals(dest[got]))
                                    break;
                            if(got!=dest.length)
                            {
                                result=rem;
                                break;
                            }
                            
                            closed.add(rem);
                            ArrayList<String> check=new ArrayList();
                            //System.out.println("removed: "+rem+" "+adj.get(rem));
                            if(adj.get(rem)!=null)
                            {
                                check=adj.get(rem);
                                
                                //System.out.println(check);
                                for(int found=0;found<check.size();found++)
                                {
                                    //System.out.println(check.get(found));
                                    String search=rem+" "+check.get(found);
                                    ArrayList<Integer> compare=interval.get(search);
                                    //System.out.println(compare);
                                    int t=time.get(rem)[0];
                                    //System.out.println(rem+" "+t);
                                    //int array[]=new int[2];
                                    //array=time.get(rem);
                                    //System.out.println(time.get("DELHI")[0]);
                                    //System.out.println(rem+" "+array[0]);
                                    
                                    //System.out.println(t_copy);
                                    if(t>23)
                                        t-=24;
                                    //System.out.println(check.get(found)+" "+t+" "+compare);
                                    boolean stop=true;
                                    for(int last=1;last<compare.size();last++)
                                        if(t==compare.get(last))
                                            stop=false;
                                    if(compare.size()==1 || stop)
                                    
                                    {
                                        if(!closed.contains(check.get(found)))
                                        {
                                            int copy[]=new int[2];
                                            copy[1]=time.get(rem)[1]+compare.get(0);
                                            //System.out.println(copy[1]);
                                            if(copy[1]>23)
                                            {
                                                copy[0]=copy[1]%24;
                                            }
                                            else
                                                copy[0]=copy[1];
                                            //System.out.println(check.get(found)+" "+copy[0]+" "+copy[1]);
                                            //check if similar vertice exists in open
                                            int insert=1;
                                            if(open.indexOf(check.get(found))!=-1)
                                            {
                                                int verify=time.get(check.get(found))[1];
                                                if(copy[1]<verify)
                                                {
                                                    int index=open.indexOf(check.get(found));
                                                    open.remove(index);
                                                    open_time.remove(index);
                                                }
                                                else
                                                    insert=0;
                                            }
                                            //time.put(check.get(found),copy);
                                            //sort open for removing least path node for next iteration
                                                
                                            //---------------
                                            if(insert==1)
                                            {
                                                //System.out.println(copy[0]+" "+copy[1]);
                                                time.put(check.get(found), copy);
                                                if(open_time.size()==0)
                                                {
                                                    open.add(check.get(found));
                                                    open_time.add(copy[1]);
                                                }
                                                else if(open_time.contains(copy[1]))
                                                {
                                                    int position=open_time.indexOf(copy[1]);
                                                    //System.out.println("position: "+position);
                                                    if(check.get(found).compareTo(open.get(position))<0)
                                                    {
                                                        
                                                        if(position==0)
                                                        {
                                                            open.addFirst(check.get(found));
                                                            open_time.addFirst(copy[1]);
                                                        }
                                                        else
                                                        {
                                                            open.add(position,check.get(found));
                                                            open_time.add(position,copy[1]);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        //int position=open_time.indexOf(copy[1]);
                                                        Iterator x = open.listIterator(position);
                                                        Iterator y = open_time.listIterator(position);
                                                        while(y.hasNext() && ((Integer)y.next()==copy[1]) && (check.get(found).compareTo((String)x.next())>0))
                                                        {
                                                            position++;
                                                        }
                                                        open.add(position,check.get(found));
                                                        open_time.add(position,copy[1]);
                                                    }
                                                }
                                                else if(copy[1]<open_time.get(0))
                                                {
                                                    open.addFirst(check.get(found));
                                                    open_time.addFirst(copy[1]);
                                                }
                                                else if(copy[1]>open_time.get(open.size()-1))
                                                {
                                                    open.addLast(check.get(found));
                                                    open_time.addLast(copy[1]);
                                                }
                                                else 
                                                {
                                                    int low=0;
                                                    int high=open_time.size()-1;
                                                    int mid=0,target=0;
                                                    while(true)
                                                    {
                                                        mid=(low+high)/2;
                                                        if(open_time.get(mid)<copy[1])
                                                            low=mid+1;
                                                        else
                                                            high=mid-1;
                                                        if(low>high)
                                                        {
                                                            target=mid;
                                                            break;
                                                        }
                                                    }
                                                    if(open_time.get(mid)>copy[1])
                                                    {
                                                        open.add(mid,check.get(found));
                                                        open_time.add(mid,copy[1]);
                                                    }
                                                    else
                                                    {
                                                        open.add(mid+1,check.get(found));
                                                        open_time.add(mid+1,copy[1]);
                                                    }
                                                }
                                                //System.out.println(open);
                                                //System.out.println(open_time);
                                            }
                                            //------------
                                        }
                                    }
                                }
                            }  
                        }
                        if(result!="")
                        {
                            //System.out.println(result+" "+(units%24));
                            opt.write(result+" "+Integer.toString(units%24));
                            opt.newLine();
                        }
                        else
                        {
                            opt.write("None");
                            opt.newLine();
                        }
                        str=br.readLine();
                    }break;
                }
            }
            opt.close();
        }
        catch(Exception e)
        {
            System.err.println("Error : "+e.getMessage());
        }
        //System.out.println(System.currentTimeMillis());
    }
}
