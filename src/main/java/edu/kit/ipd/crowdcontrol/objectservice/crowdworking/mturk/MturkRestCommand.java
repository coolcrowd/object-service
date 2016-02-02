package edu.kit.ipd.crowdcontrol.objectservice.crowdworking.mturk;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * This class abstracts a unirest request to mturk
 *
 * if a unirest exception happens a (timeout or something like that)
 * the resulting exception will contain the unique rest token and the url to call again
 * @param <T> The result of the request
 * @param <K> The Internal value which is returned by the request
 */
public class MturkRestCommand<T,K> extends CompletableFuture<T> implements Callback<String> {
    private final Function<K,T> transformer;
    private final HttpRequest request;
    private final Class<K> klass;
    private final String uniqueRestToken;

    /**
     * Creates a new command
     * @param con connection to use
     * @param uniqueRestToken unique rest token to call the operation under
     * @param operation name of the operation to call
     * @param responseGroup the group which should be returned by mturk
     * @param version version of the api to use
     * @param klass the class of the resulting structure
     * @param values the values which are needed by the operation
     * @param transformer the transformer which transforms the instance of klass into T
     */
    public MturkRestCommand(MTurkConnection con, String uniqueRestToken, String operation,
                            String responseGroup, String version,
                            Class<K> klass, Map<String, Object> values, Function<K, T> transformer) {
        this.uniqueRestToken = uniqueRestToken;
        this.klass = klass;
        this.transformer = transformer;

        request = Unirest.get(con.getUrl())
                .queryString("UniqueRestToken", uniqueRestToken)
                .queryString(con.getCallParameter(operation,responseGroup,version))
                .queryString(values);
    }
    @Override
    public void completed(HttpResponse<String> response) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(klass);
            K k = (K) context.createUnmarshaller().unmarshal(response.getRawBody());
            complete(transformer.apply(k));
        } catch (JAXBException e) {
            completeExceptionally(new RuntimeException(e));
        }
    }

    @Override
    public void failed(UnirestException e) {
        completeExceptionally(
                getDetailedException(e)
        );
    }

    /**
     * Generates a detailed exception with unique id + the command
     * @param e The exception which caused the problem
     * @return returns a exception with unique id and command
     */
    private RuntimeException getDetailedException(UnirestException e) {
        return new RuntimeException("Call "+uniqueRestToken+ " failed\n" +
                "Command: "+request.getUrl(), e);
        //for complaining people - getUrl is returning the complete command
    }

    @Override
    public void cancelled() {
        completeExceptionally(getDetailedException(null));
    }
}
