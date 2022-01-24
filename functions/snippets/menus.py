import clipboard as clip

def create(lines):
    flags = []
    names = []
    hints = []
    for line in lines:
        parts = line.split("@")
        flags.append(parts[0])
        hints.append(parts[1])
        names.append(parts[2])

    for h in hints:
        print(h)

    for n in names:
        print(n)

    result = "public static Scanner sc = new Scanner(System.in);\n\n"
    result += create_print_menu(flags, hints)
    result += create_loop(flags, names)
    result += create_methods(flags, names)

    print(result)
    clip.copy(result)


def create_print_menu(flags, hints):
    
    codes = []
    codes.append("public void printMenu(){")
    for i in range(len(flags)):
        line = '\tSystem.out.println("'
        line += flags[i]+": " + hints[i]
        line += '");'
        codes.append(line)
        codes.append('System.out.print("Enter option: ");')
    codes.append("}")

    result = ""
    for c in codes:
        result += c + "\n"

    return result

loop_str = '''
private void start(){{
    String line = "";
    while(!"{0}".equals(line)){{
        printMenu();
        line = sc.nextLine();
        if("{0}".equals(line)){{
            break;
        }}
        switch(line){{
{1}
            default:
                System.out.println("Invalid input.");
        }}
    }}
}}
'''

def create_loop(flags, names):
    cases = ""
    for i in range(len(flags)):
        if "" == names[i]:  # exit with no method name
            continue
        cases += '\t\tcase "{}":\n'.format(flags[i])
        cases += "\t\t\t{};\n".format(names[i])
        cases += "\t\t\tbreak;\n"
    abc = loop_str.format(flags[-1], cases)

    return abc

def create_methods(flags, names):
    result = ""
    for i in range(len(flags)):
        if "" == names[i]: # exit with no method name
            continue
        result += "private void {}{{\n}}\n".format(names[i])
    return result