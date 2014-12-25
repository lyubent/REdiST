package uk.co.lyubentodorov.redist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RequestController
{
    @RequestMapping("/request")
    public String request(@RequestParam(value="page", required=false, defaultValue="") String page,
                          @RequestParam(value="size", required=false, defaultValue="") String size,
                          @RequestParam(value="zoomFactor", required=false, defaultValue="") String zoomFactor,
                          @RequestParam(value="substitution", required=false, defaultValue="") String substitution,
                          HttpServletRequest httpRequest)
    {
        Request req = new Request(page, size, zoomFactor);

        System.out.println("Client IP Address " + httpRequest.getRemoteAddr());
        if (!substitution.isEmpty())
            req.executeSubstitution(substitution);

        return req.buildResponse();
    }
}
