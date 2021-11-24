package dsaa.lab02;

public class Link {
    public String ref;
    public Link(String ref) {
        this.ref=ref.toLowerCase();
    }

    public String getRef() {
        return ref;
    }

    @Override
    public String toString() {
        return ref;
    }

    @Override
    public boolean equals(Object l) {
        if (!(l instanceof Link)) return false;
        Link link = (Link) l;
        return ref.equalsIgnoreCase(link.getRef());
    }
}