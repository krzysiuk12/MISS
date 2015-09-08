package pl.edu.agh.miss.background;

/**
 * Created by krzysztofkicinger on 9/8/15.
 */
public class PathBuilder {

    protected StringBuilder pathBuilder = new StringBuilder();
    protected boolean firstRequestParameter = true;

    protected void appendRequestParameter(String paramName, String paramValue) {
        pathBuilder.append(getLinkChar()).append(paramName.toLowerCase()).append("=").append(paramValue.toLowerCase());
    }

    protected void appendRequestParameter(String paramName, String... paramValues) {
        pathBuilder.append(getLinkChar()).append(paramName.toLowerCase()).append("=");
        for(String value : paramValues) {
            pathBuilder.append(value.toLowerCase()).append(",");
        }
        pathBuilder.replace(pathBuilder.toString().length() - 1, pathBuilder.toString().length(), "");
    }

    private char getLinkChar() {
        if(firstRequestParameter) {
            firstRequestParameter = false;
            return '?';
        } else {
            return '&';
        }
    }

    public String build() {
        return pathBuilder.toString();
    }

}
