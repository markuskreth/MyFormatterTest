package de.kreth.formatter;

/**
 * Allows any Object to Format its String representation with different detail levels.
 * @author markus
 *
 */
public interface ToStringFormat {
   
   public enum Format {
      Short,
      Medium,
      Full,
      Debug
   }
   
   String toString(Format f);
}
