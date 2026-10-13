import java.util.*;

class Candidate {
    private String name;
    private int rollNumber;
    private String position;
    private int votesReceived;

    // Constructor 1: Initialize all fields
    public Candidate(String name, int rollNumber, String position, int votesReceived) {
        this.name = name.toUpperCase(); // String manipulation: display name in uppercase
        this.rollNumber = rollNumber;
        this.position = position.toUpperCase();
        this.votesReceived = votesReceived;
    }

    // Constructor 2: Initialize name, roll number, and position (default votes = 0)
    public Candidate(String name, int rollNumber, String position) {
        this(name, rollNumber, position, 0);
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getPosition() {
        return position;
    }

    public int getVotesReceived() {
        return votesReceived;
    }

    public void displayRegistration() {
        System.out.println("Candidate Registered Successfully");
        System.out.println();
        System.out.printf("%-18s : %s\n", "Candidate Name", name);
        System.out.printf("%-18s : %d\n", "Roll Number", rollNumber);
        System.out.printf("%-18s : %s\n", "Position", position);
        System.out.printf("%-18s : %d\n", "Votes Received", votesReceived);
        System.out.println();
    }
}

class ElectionSystem {
    private List<Candidate> candidates = new ArrayList<>();
    private Set<String> registeredNames = new HashSet<>();

    // Method Overloading: Register candidate with full object/details
    public void registerCandidate(Candidate candidate) {
        // String manipulation & comparison to avoid duplicate nominations
        if (registeredNames.contains(candidate.getName())) {
            System.out.println("Duplicate Candidate Check:");
            System.out.printf("%-18s : %s\n", "Candidate Name", candidate.getName());
            System.out.printf("%-18s : Duplicate Nomination\n", "Status");
            System.out.println();
            return;
        }
        registeredNames.add(candidate.getName());
        candidates.add(candidate);
        candidate.displayRegistration();
    }

    // Method Overloading: Register candidate using individual parameters
    public void registerCandidate(String name, int rollNumber, String position, int votes) {
        Candidate candidate = new Candidate(name, rollNumber, position, votes);
        registerCandidate(candidate);
    }

    public void announceResults(String targetPosition) {
        targetPosition = targetPosition.toUpperCase();
        Candidate winner = null;
        int maxVotes = -1;

        for (Candidate c : candidates) {
            if (c.getPosition().equals(targetPosition)) {
                if (c.getVotesReceived() > maxVotes) {
                    maxVotes = c.getVotesReceived();
                    winner = c;
                }
            }
        }

        System.out.println("--------- ELECTION RESULTS ---------");
        System.out.printf("%-18s : %s\n", "Position", targetPosition);
        if (winner != null) {
            System.out.printf("%-18s : %s\n", "Winning Candidate", winner.getName());
            System.out.printf("%-18s : %d\n", "Total Votes", winner.getVotesReceived());
        } else {
            System.out.println("Winning Candidate  : NONE");
            System.out.println("Total Votes        : 0");
        }
        System.out.println("======================================");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("========== STUDENT COUNCIL ELECTION ==========");
        System.out.println();

        ElectionSystem election = new ElectionSystem();

        // Registering candidates using overloaded methods and constructors[cite: 7]
        election.registerCandidate("Ananya", 101, "President", 125);
        election.registerCandidate(new Candidate("Rahul", 102, "President", 98));

        // Testing duplicate nomination check[cite: 7]
        election.registerCandidate("Ananya", 103, "President", 50);

        // Announcing election results[cite: 6, 7]
        election.announceResults("PRESIDENT");
    }
}