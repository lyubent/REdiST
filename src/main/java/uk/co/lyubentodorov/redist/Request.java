package uk.co.lyubentodorov.redist;

import org.json.JSONArray;
import org.json.JSONObject;

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

    /**
     * assuming we have an array of:
     * {'substitution': [{'destination':'Bulgaria', 'source': 'France'},
     *                  {'destination':'Portugal', 'source': 'Russia'}}
     * and we want to end up with:
     * {'substitution': [{'destination':'France', 'source': 'Bulgaria'},
     *                   {'destination':'Russia', 'source': 'Portugal'}}
     * @param jsonObjArray String notation of the json array.
     * @return JSONObject storing the swapped json array.
     */
    public JSONObject executeSubstitution(String jsonObjArray)
    {
        JSONObject myjson = new JSONObject(jsonObjArray);
        JSONArray jsonArray = myjson.getJSONArray("substitution");
        JSONArray ja = new JSONArray();

        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject tempJSON = jsonArray.getJSONObject(i);
            String source = tempJSON.getString("source");
            int dest = tempJSON.getInt("destination");

            // build the array with swapped values
            ja.put(new JSONObject().put("source", dest).put("destination",source));
        }

        JSONObject mainObj = new JSONObject();
        mainObj.put("substitution", ja);

        return mainObj;
    }
}
