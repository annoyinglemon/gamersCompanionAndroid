package lemond.annoying.gamerscompanion;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

//DO NOT USE @Singleton ANNOTATION, IT IMPLIES THAT THIS IS THE SINGLETON FOR THE ENTIRE APPLICATION
// THIS SCOPE IS ONLY A SINGLETON WITHIN THE CREATED INSTANCE OF A MODULE

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface GamersApplicationScope {}
