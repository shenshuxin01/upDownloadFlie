package cn.shenshuxin.updownloadfile.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.StartupStep;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Supplier;

@Slf4j
public class SsxStartupStep implements ApplicationStartup {
    @Override
    public CustomStartupStep start(String stepId) {
        return new CustomStartupStep(stepId);
    }

    private static class CustomStartupStep implements StartupStep {

        public CustomStartupStep(String stepId) {
            log.info("CustomStartupStep构造方法："+stepId);
            System.out.println("CustomStartupStep构造方法："+stepId);
        }

        /**
         * Return the name of the startup step.
         * <p>A step name describes the current action or phase. This technical
         * name should be "." namespaced and can be reused to describe other instances of
         * similar steps during application startup.
         */
        @Override
        public String getName() {
            log.info("getName：");
            System.out.println("getName：");
            return "default";
        }

        /**
         * Return the unique id for this step within the application startup.
         */
        @Override
        public long getId() {
            log.info("getId：");
            System.out.println("getId：");
            return 0;
        }

        /**
         * Return, if available, the id of the parent step.
         * <p>The parent step is the step that was started the most recently
         * when the current step was created.
         */
        @Override
        public Long getParentId() {
            log.info("getParentId：");
            System.out.println("getParentId：");
            return null;
        }

        /**
         * Add a {@link Tag} to the step.
         *
         * @param key   tag key
         * @param value tag value
         */
        @Override
        public StartupStep tag(String key, String value) {
            log.info("tag："+key);
            System.out.println("tag："+key);
            return this;
        }

        /**
         * Add a {@link Tag} to the step.
         *
         * @param key   tag key
         * @param value {@link Supplier} for the tag value
         */
        @Override
        public StartupStep tag(String key, Supplier<String> value) {
            log.info("tag："+key);
            System.out.println("tag："+key);
            return this;
        }

        /**
         * Return the {@link Tag} collection for this step.
         */
        @Override
        public Tags getTags() {
            return new DefaultTags();
        }

        /**
         * Record the state of the step and possibly other metrics like execution time.
         * <p>Once ended, changes on the step state are not allowed.
         */
        @Override
        public void end() {

        }
        static class DefaultTags implements StartupStep.Tags {

            @Override
            public Iterator<StartupStep.Tag> iterator() {
                return Collections.emptyIterator();
            }
        }
    }
}
