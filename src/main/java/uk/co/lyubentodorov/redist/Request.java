package uk.co.lyubentodorov.redist;

/**
 * Model for the request. Stores values associated with the request.
 * and carries out validation.
 */
public class Request
{
    private final String page;
    private final String size;
    private final String zoomFactor;

    public Request(String page, String size, String zoomFactor)
    {
        this.page = page.trim();
        this.size = size.trim();
        this.zoomFactor = zoomFactor.trim();
    }

    public String buildResponse()
    {
        // check if values are in db.
        String pageEntry = verifyEntry(RedisClient.getClient().fetch(page), "page");
        String sizeEntry = verifyEntry(RedisClient.getClient().fetch(size), "size");
        String zoomFactorEntry = verifyEntry(RedisClient.getClient().fetch(zoomFactor), "zoom factor");

        return String.format("[\"page - %s\", \"size - %s\", \"zoomFactor - %s\"]",
                pageEntry, sizeEntry, zoomFactorEntry);
    }

    private String verifyEntry(String entry, String type)
    {
        if (entry == null || entry.equals(""))
            return String.format("Entry for %s is empty", type);
        return entry;
    }
}
