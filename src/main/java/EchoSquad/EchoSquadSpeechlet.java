/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package EchoSquad;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

/**
 * This simple sample has no external dependencies or session management, and shows the most basic
 * example of how to handle Alexa Skill requests.
 */
public class EchoSquadSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(EchoSquadSpeechlet.class);

    /**
     * Array containing space facts.
     */
	private int talkedToLebron = 0;
	
    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any initialization logic goes here
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getLebronResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("HeyLebron".equals(intentName) && (talkedToLebron == 0)) {
			talkedToLebron = 1;
			return getLebronResponse();
		} else if ("MyDayGood".equals(intentName) && (talkedToLebron > 0)) {
			return getDayResponse("good");
		} else if ("MyDayBad".equals(intentName) && (talkedToLebron > 0)) {
			return getDayResponse("bad");
		} else if (("PlayLBJmusic").equals(intentName) && (talkedToLebron > 0)){
			return playMusic();
		}
		else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any cleanup logic goes here
    }


    /**
     * Returns a response for the help intent.
     */
    private SpeechletResponse getHelpResponse() {
        String speechText = "";

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }
	
	private SpeechletResponse playMusic() {
        String speechText = "";
		
		String remindStudent = "It's been more than a week since you've gave us an update.";
        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }
	
    /**
     * Returns a response for the HeyLebron intent.
     */
    private SpeechletResponse getLebronResponse() {
        String speechText = "Hey how was your day?"; //This is Lebron James response.

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        return SpeechletResponse.newAskResponse(speech, reprompt);
    }
    
	
	private String[] goodResponses = new String[]{
		"That's really good! Keep it up.",
		"Proud of you. Keep making progress.",
		"You da bomb!",
		"You're gonna do great things!",
		"You make me proud!"
		
	};
	private String[] badResponses = new String[]{
		"Oh, I'm sorry. Don't be afraid to get help.",
		"I'm sorry to hear that. I hope your day gets better.",
		"Fall seven times stand up eight.",
		"A problem is a chance for you to do your best.",
		"Feel free to talk to a teacher you trust."
		};
	
    // intent is student's answer to how their day was
    private SpeechletResponse getDayResponse(String s) {
        int dataIndex = 0;
        String speechText = ""; //This is Lebron James response.
        if (s.equals("good"))
			speechText = goodResponses[dataIndex];
        else if (s.equals("bad"))
			speechText = badResponses[dataIndex];

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }
    
    
}
