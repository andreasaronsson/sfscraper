/**
 * 
 */
package com.aron.svenskafansscraper;

/**
 *
 */
class Post {
    private final String name;
    private final String sent;
    private final String post;

    Post(String name, String sent, String post) {
        this.name = name;
        this.sent = sent;
        this.post = post;
    }

    /**
     * @return the name
     */
    final String getName() {
        return name;
    }

    /**
     * @return the sent
     */
    final String getSent() {
        return sent;
    }

    /**
     * @return the post
     */
    final String getPost() {
        return post.replaceAll("'", "''");
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((post == null) ? 0 : post.hashCode());
        result = prime * result + ((sent == null) ? 0 : sent.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (post == null) {
            if (other.post != null)
                return false;
        } else if (!post.equals(other.post))
            return false;
        if (sent == null) {
            if (other.sent != null)
                return false;
        } else if (!sent.equals(other.sent))
            return false;
        return true;
    }

}
