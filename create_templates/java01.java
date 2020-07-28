```file
/**
 * ${classComment}
 */
public class ${classname} ${parent}{
${fields}

${methods}
}
```
```field
    ${comment}
    ${modifier} ${type} ${name};
```
```getter
    ${comment}
    ${modifier} ${type} ${methodName}(){
        return ${name};
    }
```
```setter
    ${comment}
    ${modifier} void ${methodName}(${type} ${name}){
        this.${name} = ${name};
    }
```
```string
    ${modifier} String toString(){
        StringBuilder sb = new StringBuilder("{");
        ${appendFields}
        sb.append("}");
        return sb.toString();
    }
```

