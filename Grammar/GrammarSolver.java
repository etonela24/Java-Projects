import java.util.*;

/**
 * The GrammarSolver class represents a grammar solver that can generate sentences based on given grammar rules.
 * It uses a map to store the grammar rules and provides methods for checking symbol presence,
 * retrieving symbols, and generating sentences.
 *
 * @Author: Ornela Amouzou-Adoun
 */
public class GrammarSolver {
    private Map<String, String[]> grammarMap;
    private Random random;

    /**
     * Constructs a GrammarSolver object with the provided grammar rules.
     *
     * @param rules the list of grammar rules
     * @throws IllegalArgumentException if the rules list is null or empty
     */
    public GrammarSolver(List<String> rules) {
        if (rules == null || rules.size() == 0) {
            throw new IllegalArgumentException("Grammar rules list cannot be null or empty.");
        }

        grammarMap = new TreeMap<>();
        loadGrammar(rules);
        random = new Random();
    }

    /**
     * Loads the grammar rules into the map.
     *
     * @param rules the list of grammar rules
     * @throws IllegalArgumentException if multiple lines for the same non-terminal symbol exist in the grammar
     */
    private void loadGrammar(List<String> rules) {
        Set<String> nonTerminals = new HashSet<>();

        for (String rule : rules) {
            String[] parts = rule.split("::=");
            String symbol = parts[0].trim();
            String[] expansions = parts[1].trim().split("\\|");

            if (nonTerminals.contains(symbol)) {
                throw new IllegalArgumentException("Grammar contains multiple lines for the same non-terminal symbol: " + symbol);
            }

            grammarMap.put(symbol, expansions);
            nonTerminals.add(symbol);
        }
    }

    /**
     * Checks if the specified symbol is present in the grammar rules.
     *
     * @param symbol the symbol to check
     * @return true if the symbol is present, false otherwise
     * @throws IllegalArgumentException if the symbol is null or empty
     */
    public boolean contains(String symbol) {
        if (symbol == null || symbol.length() == 0) {
            throw new IllegalArgumentException("Symbol cannot be null or empty.");
        }

        return grammarMap.containsKey(symbol);
    }

    /**
     * Retrieves the set of symbols in the grammar.
     *
     * @return the set of symbols
     */
    public Set<String> getSymbols() {
        return grammarMap.keySet();
    }

    /**
     * Generates a sentence based on the specified symbol.
     *
     * @param symbol the symbol to generate a sentence for
     * @return the generated sentence
     * @throws IllegalArgumentException if the symbol is null or empty
     */
    public String generate(String symbol) {
        validateSymbol(symbol);

        if (!grammarMap.containsKey(symbol)) {
            return symbol; // Treat it as a terminal symbol
        }

        String[] expansions = grammarMap.get(symbol);
        String expansion = getRandomExpansion(expansions);
        StringBuilder sb = new StringBuilder();

        String[] subSymbols = splitIntoSymbols(expansion);
        for (String subSymbol : subSymbols) {
            sb.append(generate(subSymbol)).append(" ");
        }

        return sb.toString().trim();
    }

    private void validateSymbol(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty.");
        }
    }

    private String getRandomExpansion(String[] expansions) {
        int randomIndex = random.nextInt(expansions.length);
        return expansions[randomIndex];
    }

    private String[] splitIntoSymbols(String expansion) {
        return expansion.trim().split("\\s+");
    }
}
