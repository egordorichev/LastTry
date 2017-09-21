package org.egordorichev.lasttry.system.entity.componentSystem;

import org.terasology.entitysystem.core.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface BindEventHandler {
    Class<? extends Component>[] filter() default {};
}
