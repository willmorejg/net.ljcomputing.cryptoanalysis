/**
           Copyright 2016, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.cryptoanalysis;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class to get the character frequencies for a given series of characters. 
 *
 * @author James G. Willmore
 */
public class MainCounter {
  
  /** The key size. */
  private int keySize = 0;
  
  /** The source. */
  private String source;
  
  /** The counts. */
  private Map<String, Integer> counts = new HashMap<String, Integer>();

  /**
   * Instantiates a new main counter.
   *
   * @param keySize the key size
   * @param source the source
   */
  public MainCounter(int keySize, String source) {
    this.keySize = keySize;
    this.source = source;
  }

  /**
   * Gets the letter frequency.
   *
   * @return the letter frequency
   */
  public Map<String, Integer> getLetterFrequency() {
    char[] chars = source.toCharArray();
    int end = chars.length - 1;
    StringBuffer keyBuf = new StringBuffer();

    if (null != chars && chars.length > end) {
        keyBuf.setLength(keySize);

        for (int c = 0; c < end;) {
          String key = buildKey(keySize, ++c, chars);

          putKey(keySize, counts, key);
        }
    }

    return counts;
  }

  /**
   * Prints the results.
   *
   * @param counts the counts
   */
  public void printResults(Map<String, Integer> counts) {
    Set<String> keys = new TreeSet<String>(counts.keySet());

    for (String key : keys) {
      System.out.println(key + ": " + counts.get(key));
    }
  }

  /**
   * Open file.
   *
   * @param file the file
   * @return the string
   * @throws Exception the exception
   */
  @SuppressWarnings("resource")
  public static String openFile(File file) throws Exception {
    return new Scanner(file).useDelimiter("\\Z").next();
  }

  /**
   * Builds the key.
   *
   * @param size the size
   * @param start the start
   * @param charArray the char array
   * @return the string
   */
  private String buildKey(int size, int start, char[] charArray) {
    int index = size - 1;
    int end = (size + start);
    String key = null;

    if (start > index && start < end) {
      char[] copy = Arrays.copyOfRange(charArray, start, end);
      key = String.valueOf(copy);
    }

    return key;
  }

  /**
   * Put key.
   *
   * @param size the size
   * @param counts the counts
   * @param key the key
   */
  private void putKey(int size, Map<String, Integer> counts, String key) {
    if (null != key && key.length() == size) {
      if (null != counts.get(key)) {
        Integer count = counts.get(key) + 1;
        counts.put(key, count);
      } else {
        counts.put(key, 1);
      }
    }
  }

  /**
   * The main method; used for unit testing.
   *
   * @param args the arguments
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception {
    ClassLoader classLoader = MainCounter.class.getClassLoader();
    File file = new File(classLoader.getResource("test.txt").getFile());
    String source = MainCounter.openFile(file);
    
    MainCounter counter = new MainCounter(3, source);
    Map<String, Integer> counts = counter.getLetterFrequency();

    counter.printResults(counts);
  }
}
