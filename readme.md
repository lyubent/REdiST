##REdiST = Redis + REST

REdiST is a REST service that uses Redis as it's data cache. It was developed as part of a 2 hour coding interview and took me a total of 32 minutes. The task was to create a REST service that used a NoSQL store in Java and handles a GET request from which it extracts 3 parameters, page, size, zoomFactor, checks whether an entry for these parameters are present in a cache and returns the stored value as a string.

License
----

[Apache v2]


[Apache v2]:http://www.apache.org/licenses/LICENSE-2.0