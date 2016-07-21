package saneet.algosplay.saneet.algosplay.misc;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Problem: input will be a seres of page-user mappings per line.
 * Affinity between two pages increases if the same user viewed them.
 * Find the page pair with the highest affinity
 */
public class PageViewAffinityProblem {

    //Id generators
    //These will be used to reduce string parsings so that the next hashmaps
    // don't have to parse the strings repeatedly to calculate hashcode
    private int userIdCount = 0;
    private int pageIdCount = 0;

    private int maxCount = 0;
    Page page1, page2 = null;

    //Will be used to store all objects
    HashMap<String, Page> pagesMap = new HashMap<String, Page>();
    HashMap<String, User> usersMap = new HashMap<String, User>();

    public void processLog(String pageUrl, String userName){

        User user = usersMap.get(userName);
        if (user == null) {
            user = new User(userName, ++userIdCount);
        }

        Page page = pagesMap.get(pageUrl);
        if (page == null) {
            page = new Page(pageUrl, ++pageIdCount);
        }

        user.addPageView(page);

    }

    public void printMaxAffinityPages() {
        System.out.println(page1);
        System.out.println(page2);
        System.out.println("Affinity: " + maxCount);
    }



    private final class Page{
        public final String name;
        public final int id;

        //Lazy initialization
        private HashMap<Page, Integer> pageAffinities;

        public Page(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public void addPageAffinity(Page page){
            if (pageAffinities == null) {
                pageAffinities = new HashMap<>();
            }

            //Update count
            int count = pageAffinities.getOrDefault(page, 0);
            count++;
            pageAffinities.put(page, count);

            //Check if max
            if (count > maxCount) {
                page1 = page;
                page2 = this;
            }

        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            return id == ((Page) obj).id;
        }
    }

    private final class User{
        public final String name;
        public final int id;

        private HashSet<Page> pageViews = new HashSet<Page>();

        public User(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public void addPageView(Page page) {
            if (pageViews.contains(page)) {
                return;
            }

            for (Page pageView : pageViews) {
                pageView.addPageAffinity(page);
                page.addPageAffinity(pageView);
            }

            pageViews.add(page);
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            return id == ((Page) obj).id;
        }
    }
}

