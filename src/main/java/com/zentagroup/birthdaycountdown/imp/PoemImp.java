package com.zentagroup.birthdaycountdown.imp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zentagroup.birthdaycountdown.dto.Poem;
import com.zentagroup.birthdaycountdown.exception.CouldNotGetPoemsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.zentagroup.birthdaycountdown.util.Constant.*;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

@Service
public class PoemImp {

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Gets a list of Poem objects from an external API using restTemplate.
     * @return List<Poem> list of Poems
     * @throws CouldNotGetPoemsException if an error occurs getting a response form the poemist api
     */
    public List<Poem> getPoems() throws CouldNotGetPoemsException{
        final String uri = POEMIST_URL + "randompoems";
        String response;
        try{
            response = restTemplate.getForObject(uri, String.class);
        } catch (Exception ex){
            throw new CouldNotGetPoemsException(ERROR_FETCHING_DATA);
        }
        return new Gson().fromJson(response, new TypeToken<ArrayList<Poem>>() {
        }.getType());
    }

    /**
     * Returns a Poem from a random index of a Poems List.
     * @return Poem from random index
     */
    public Poem getRandomPoem(List<Poem> poems){
        Random r = new Random();
        return poems.get(r.nextInt(poems.size() -1));
    }

}
