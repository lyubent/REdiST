package uk.co.lyubentodorov.redist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController
{
    @RequestMapping("/request")
    public String request(@RequestParam(value="page", required=false, defaultValue="") String page,
                          @RequestParam(value="size", required=false, defaultValue="") String size,
                          @RequestParam(value="zoomFactor", required=false, defaultValue="") String zoomFactor)
    {
        Request req = new Request(page, size, zoomFactor);
        return req.buildResponse();
    }
}
