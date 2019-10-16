import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

/**
 * The Class Competition creates an arraylist called contestant of the objects in the Class 
 * Personality. Users are able to create a new Personality object from this class and will 
 * add it to the arraylist contestant. Users are able to see the size of the arraylist. Users 
 * can see the details of each instance in that arraylist by calling the method list(). Users are 
 * able to increase the votes of a particular contestant by using the voteFor() method.
 * The user will input the name for which they wish to add a vote to, the method will search for that 
 * name within the arraylist if the name is found then that contestant's votes will increment by 1, 
 * however, if the name does not match any contestant the method will output an error message. If the 
 * user wishes to get the shortlist of contestants they will be asked for the minimum vote 
 * for the contestants to have and then the method will again search the arraylist to see whether 
 * each contestant's votes and less than the minimum vote. If it is less then that contestant 
 * will be removed from the arraylist. Also if the user wishes to get the top most contestants 
 * they will need to input the amount of contestants they want. The method will sort the contestants
 * by number of votes, and it will create a new sorted arraylist with the set size of the parameter 
 * that the user inputted. It will then return the new arraylist with the number of contestants that 
 * made it to the top most. However, if the user would like the top 3 contestants and the 3rd 
 * contestant has the same number of votes as the 4th contestant the method will then increase the 
 * input by 1 so that the arraylist has the capacity to add another instance. Then the 4th instance 
 * will be added to the new arraylist.
 * 
 * @author Amelia Gaskin
 * @version 14/11/2018
 */
public class Competition
{
    private ArrayList<Personality> contestant;
    /**
     * Constructor for objects of class PersonalityList
     */
    public Competition()
    {
        contestant = new ArrayList <Personality>();

    }

    /**
     * Add a new personality to the competition's list of personality.
     * @param personality The personality object to be added.
     */
    public void addPersonality(Personality competitor)
    {
        contestant.add(competitor); 
    }

    /**
     * @return The number of competitors (Personality objects) in the 
     * competition.
     */
    public int getSize()
    {
        return contestant.size();
    }

    /**
     * List all the Personalities, with name, sport and the
     * number of votes in the collection, on different lines.
     */
    public void list()
    {
        for(int index = 0; index < contestant.size(); index ++) {
            Personality person = contestant.get(index);
            String details = person.getDetails();
            System.out.println(details);
        }
    }

    /**
     * Increment the vote variable by 1 for specific personality
     * if personality is found in collection 
     * @param name, if not found print error message.
     */
    public void voteFor(String name)
    {
        int index = 0;
        boolean searching = true;
        while (searching && index < contestant.size()) {
            Personality person = contestant.get(index);
            String aName = person.getName().toLowerCase();
            if (name.toLowerCase().equals(aName)) {
                person.increaseVotes(+1);
                searching = false;
            }
            else {
                index ++;
            }
        }
        if (searching) {
            System.out.println("Error, couldn't find the personality " + name);         
        }
    }

    /**
     * Find all personalities that have vote 
     * variable less than @param minimumVote 
     * and remove them from the list.
     */
    public void shortlist(int minimumVote)
    {
        Iterator <Personality> it = contestant.iterator();
        while (it.hasNext()) {
            Personality competitors = it.next();
            int personVotes = competitors.getVotes();
            if (personVotes < minimumVote) {
                it.remove();
            }
        } 
    }

    /**
     * Sort the arrayList contestant by number of votes, add the Personality 
     * objects in the sorted arrayList to a new arrayList called mostVotes.
     * mostVotes with the set size 
     * @param top.
     * If index value is more than top, then check whether the previous 
     * object in the contestant arrayList has the same number of votes to the 
     * current object in the contestant arrayList. If they are the same value, 
     * then increment top by 1. If they are not hte same value then
     * @return ArrayList<Personality> mostVotes.
     */
    public ArrayList<Personality> getMost(int top)
    {
        ArrayList<Personality> mostVotes = new ArrayList<Personality>(top);
        Collections.sort(contestant, new Comparator<Personality>()
            {
                public int compare(Personality p1, Personality p2)
                {
                    return p2.getVotes() - p1.getVotes();  
                }
            });
        for(int index = 0; index < contestant.size(); index ++) {

            if(mostVotes.size() != top){
                Personality person = contestant.get(index);
                mostVotes.add(person);            
            }
            else if(top > 0 && mostVotes.size() == top){
                int i = top -1;
                Personality prevPerson = mostVotes.get(i);
                Personality currentPerson = contestant.get(index);
                if(prevPerson.getVotes() == currentPerson.getVotes()){
                    top ++;
                    mostVotes.add(currentPerson);
                }

            }
        }
        return mostVotes;     
    }
}
