package com.lychee.amz.analytics.advice;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ConstraintViolationExceptionHelp {

    public static List<String> getViolationMessages(ConstraintViolationException ex) {
        List<String> violations = new ArrayList<>();

        Iterator<ConstraintViolation<?>> itr = ex.getConstraintViolations().iterator();
        while(itr.hasNext()) {
            ConstraintViolation<?> v = itr.next();
            String path= getPropertyPath(v);
            String message = v.getMessage();

            violations.add(String.format("%s: %s", path, message));
        }

        return violations;
    }

    private static String getPropertyPath(ConstraintViolation v) {
        Path path = v.getPropertyPath();
        if (path == null || path.iterator() == null) {
            return "";
        }

        List<String> paths = new ArrayList<>();
        Iterator<Path.Node> itr = path.iterator();

        while(itr.hasNext()) {
            Path.Node node = itr.next();
            if (node.getKind() == ElementKind.PROPERTY) {
                paths.add(node.getName());
            }
        }

        return String.join(".", paths);
    }
}
