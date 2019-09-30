import java.util.*;

public class KMP {
	
	private static boolean isMatch(String text, String pattern, int[] prefixArray) {
		int i = 0;
		int j = 0;
		
		while (i < text.length()) {
			boolean isMatch = true;
			for (; j < pattern.length(); j++, i++) {
				if (text.charAt(i) != pattern.charAt(j)) {
					j = prefixArray[j - 1];
					isMatch = false;
					break;
				}
			}
			if (isMatch) {
				return true;
			}
		}
		
		return false;
	}
	
	private static List<Integer> occurrences(String text, String pattern, int[] prefixArray) {
		List<Integer> occurrencesList = new ArrayList<>();
		
		int j = 0;
		
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = prefixArray[j - 1];
			}
			if (text.charAt(i) == pattern.charAt(j)) {
				j++;
			}
			if (j == pattern.length()) {
				occurrencesList.add(i - pattern.length() + 1);
				j = prefixArray[j - 1];
			}
		}
		
		return occurrencesList;
	}
	
	private static List<Integer> nonOverlappingOccurences(String text, String pattern, int[] prefixArray) {
		List<Integer> occurencesList = new ArrayList<>();
		
		if (pattern.isEmpty() && text.isEmpty()) {
			occurencesList.add(0);
			return occurencesList;
		}
		
		if (pattern.isEmpty()) {
			for (int i = 0; i < text.length(); i++) {
				occurencesList.add(i);
			}
			return occurencesList;
		}
		
		int j = 0;
		
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = prefixArray[j - 1];
			}
			if (text.charAt(i) == pattern.charAt(j)) {
				j++;
			}
			if (j == pattern.length()) {
				occurencesList.add(i - pattern.length() + 1);
				j = 0;
			}
		}
		
		return occurencesList;
	}
	
	private static Set<String> numberOfUniqueSubstrings(String text) {
		int[] prefixArray = prefixFunction(text);
		Set<String> substrings = new HashSet<>();
		
		// approach 1: using sets
		for (int i = 0; i < prefixArray.length; i++) {
			for (int j = i - prefixArray[i]; j >= 0; j--) {
				substrings.add(text.substring(j, i + 1));
			}
		}
		
		// approach 2: without using sets
		int number = 1; // 1 is for empty string
		
		String temp = "";
		
		for (int i = 0; i < text.length(); i++) {
			String reverse = new StringBuilder(temp + text.charAt(i)).reverse().toString();
			
			int[] prefix = prefixFunction(reverse);
			int max = Arrays.stream(prefix).max().getAsInt();
			number += temp.length() + 1 - max;
			temp += text.charAt(i);
		}
		
		System.out.println(number);
		return substrings; 
	}
	
	/* taken from https://www.geeksforgeeks.org/count-number-of-distinct-substring-in-a-string/
	 * just to match numbers with my own implementation
	 */
	private static Set<String> distinctSubstring(String str) 
    { 
        // Put all distinct substring in a HashSet 
        Set<String> result = new HashSet<String>(); 
  
        // List All Substrings 
        for (int i = 0; i <= str.length(); i++) { 
            for (int j = i + 1; j <= str.length(); j++) { 
  
                // Add each substring in Set 
                result.add(str.substring(i, j)); 
            } 
        } 
  
        // Return size of the HashSet 
        return result; 
    } 
	
	private static int[] prefixFunction(String str) {
	    /* 1 */
	    int[] prefixFunc = new int[str.length()];
	 
	    /* 2 */
	    for (int i = 1; i < str.length(); i++) {
	        /* 3 */
	        int j = prefixFunc[i - 1];
	 
	        while (j > 0 && str.charAt(i) != str.charAt(j)) {
	            j = prefixFunc[j - 1];
	        }
	 
	        /* 4 */
	        if (str.charAt(i) == str.charAt(j)) {
	            j += 1;
	        }
	 
	        /* 5 */
	        prefixFunc[i] = j;
	    }
	 
	    /* 6 */
	    return prefixFunc;
	}
	
	private static void printList(List<Integer> list) {
		for (int i : list) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public static List<Integer> KMPSearch(String text, String pattern) {
	    int[] prefixFunc = PrefixFunction.prefixCalculator(pattern + '#' + text);
	    ArrayList<Integer> occurrences = new ArrayList<Integer>();
	 
	    for (int i = pattern.length() + 1; i < prefixFunc.length; i++) {
	        if (prefixFunc[i] == pattern.length()) {
	            occurrences.add(i - 2 * pattern.length());
	        }
	    }
	 
	    return occurrences;
	}
	
	private static void checkIsMatchFunction() {
		String text = "gymkuurmyzcxkuzvoukuzjvsqujabigfqneoigwmjgrgdjemtwmhrrfjqyrsiufjnvqqnorcbghkhtfbaggjohnuzdewpwmjrjxctkhefhctswvrswhktovduurngvbxxncmvnqjcdhgbzeichyzshflehqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxcxkzjuopderzdutjihppcxeuhreyqtroaqxkujwpxqizzapmedahkodtiyrxexrtcnuijgblutlwljprraanlqaeahlmhtxkhwayvpoaqnfwiblrcruwlfrdeangvrhnpmsfmcqrengcrleojmmzjohibdeljdbkrvzfuoodrsdqdevgkmmwrqnzodmkjaqoalhampazfgrqrmajpiejyjidwenrhxikfoplcyxfpdgqdbkhqiynfpgvswomhbbqvtsztvduijwoapzbfbqymajebnvnqjloheenzprirzysvkvytllwcnhtwswwucnaezwcwpgytzndnjfrdgjodtahsppogkvrcxemxpyadewizdkedstprsfzzdqittrgwfnzdyivoaryekyrtftgwnrdfcakisnhonfiswepdbuadbuhbfljfjgowanijcmvhpmvwirgdqxgjgcijxovkwwfsscxndasvihsxqtfiyprsyygbfzbzgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxsorjkvenfldcbmfwtugryminvoapqekncgchbyiuzqbqoxksgxgxaothpfgxwjnumekutxkomghpiejhscwsssjiwdlyvleqotxwlwtekrlbtmtjlkulqiuznsslmskzwmthekziusbmounzzmeymiywjsyyiukirxqxdjahauxqqqjarhjbydedgyqguljdyutrvswbbrwpfvcuquouroixjraqgwghtrwqvasixlvsfclxlhkztomdljhnuejaotwmzerkohpchcnsgkgvwpizqupujjkokacynxhnkyupmzdgxonpelpqcqqbtebxlzfowzbepibehqhcblgylyobikkzhqmdhywkvejegomuhipznjyuxvdahzyqxjnllqfuynnrpnocsebnsiguwflptqszgaqtyinwxcdmkbizpptldqxbkfipxjggjstticpcqloelzpganmeljyzisbeajyjjsanzbyumrcolrgusokfvsprmzekedbouqhgclbncosroblquvgbdkoigcddkkhpzfktaudrmoqffzloynwyhhvwkcsnmcuuhfyxskavnhgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxhlcncjikekmgssesxvvsggaeovqsqjuvblqpeycwsrwzsxkslupcnjgvgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxagncuwkppvahjzxikhbzzdnphlyavcmlwjwxaovkvhsnuoriyybbkanmfuctbvrjlgblzwzyqizvjuuvpaqsagzhzlczoealmyimoebfywqoqwmgmwttaaiadvtznfgiqsrseumdeqwrzefxgbffrnbvuizaogtphnpwlqthuqmobogfuynaetvtxtaumiulirksfozwpylabefepjlcrsoavrwqkvnchcljxjxwncgspdydzwmugdpwhfwkfoyrryrljngdxboqmtumtnkztwfvpxyekknbvkabfjfohakfxocyhnrlyxaudfpxnkxrwgllibfehburfbynktvihwjyqcjyzviytsmhrydgrecjggouythplvedfughhpjjiuijenhpqvdzorjppjlesbelzhpfuwbhgjlfxteiklcbmbsqmujxyaqpmkeiwfvaotamqwfgtsdtfqxpxyanpfhlfnfisgrpvuszeokyixazoakcmvskzknzwkhkzezqrhulslhzicvuelwzsdvwkeheqalwrrcnniwrpgwvdhkvubcakejplyiphkulkunubwzsntjdijsucdqinkkztgwehhozgmfaurzucublkptolbqfkkylkvsftspsezjpucdqwcbqbowbxmgzpswqwbkzzgugyzqtixyjsgiihyebvaqpbnnrzcnuigrpgvcyuaijqnthvuenklcztomxcdshvpyjgqiojoubjadbikgjlmadwztduvdftdtcpcjqoxbidoplreqdzplnuxzhsnprbncathnfwbexytyodseyowxiqhmyowfykorisafwkhhkyxnosnbcuftgfzyghddmdztpujgqcgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxpremsvwasrtpbfbmypxvubcsqvwjloqkbxtruifcqxxatlrtcbklazaockvvvmvzhfudkvrmuyqyfmmxdjvlgofrynahohnldamqutdbmqjicvmrllwemskxyywfihioiekcsdqbcnjjzamgjeumhrlyqqctghnyrqsuwimgisafhaiegqaigcsmucobavhofearybogkcngzxnvdlfgauarbzldrfbqhbtzczqqbponpblmegibnrcxqlnbmnsuvftiixtffbblvllbdqgxssjufvbtimtsgejuqxuhckiwdnoqiblavhyqhthvrouimoqwrhbfsgzcovnqnhseqnvlogugcbpnfdebzyjxjcbcerrchzyfhmmhsvbhaqdjjvbbjecrsphqpxdslqajnwwnjyczyghyblnpkjipzfksuwuqnznrvevtprdaujkzmebqopzjtzujihcrijobecoafsxbvydbcdkjdcvxshqxdrsqjpeifihfscxnputeooxsqguxurtnsteluwaxidxpbhierghgpsomreayrkogelearyxrabdjyutemvxvletmczqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxyvlgvupcjlzmjnpevdiwywuifdidkqrshtokabnrbloqmfwxyyuagjbvbavamkjeudvmjpcculwmzwifygmwhietnrnstebqjigdrrszlgrqkuoeuonrxitwxhwmpeafspcxomyragzzwlchilokdszepvpufpfrvlmtltbgzfmmrkytmwwpprgyjwajhnslapltjbwiplpkknjukdiplcnuznwoftrjajzpyoncuwahwwtrkvnjttqzxognyavnvbfpfzgprnsrcejrrjazjzmarwysaszbtemyqkyzbdgnwgucbolpkaviumvhmgomuzvpgjntwatazrihbihrjyskbqzxzvbdiocuzhmexhytgzmhbymulccgpyixbiiulvcuryqkdfmukpivatkttigrakghaandvemwvsfkjnnhwvfhujwypbyvoeyyqvhnsixeavhfxzkmvyxqlrzrpggdruzrbaempklpfbccclndtwfymrcagmyqtscosdyfbbgiivxdeaszdxjuwtiwrydttleloutzphauktuankzxjzjkzyumnclsnhvyoaaygjviwviicfkjlyzqenhsqoormlgkwrrzktlwvkgywzxejvsqgjkclivzhrvxtkoptgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxxmexueodqkcavkslbcqvnozexlelqulkamxwwuqmvpaamedaehscqgftludhvrzgjuwubejxgltlbafvpfzswlfkbwsizpmoexnnnlsfevaenyzhvworgurhceoihxxatskuenqlyvagtxnesvimjstoqibfarlywmvlfcamdjccrzirjplxqkgpgjmoykdjlfmqwuzyzaueucznofixxwbymtningbimjjahfagtrcllqwdtanhssuomjdgowqptbqsnzylmxvzwxetaprgrhqgfkdssfrefhtsmmhpgghbwjoqducsjjznwjnsokhmtiecqeutfeolypttalhyvppoeyxrvmrcwlbukgydgbvswrezlhpuvqahoievwiwrvpfrbklnonuabafwdncnuvkromaagheqvlmhvepksdjvosalouiingzzpmczyhmmkellcauqaiubuvxnrclcuqaoezmuyhmwauuxfezethtfrpsirdedwctgbqpxtrcgagalgejuiiagapkvxpmizseyypsjltnuzgnpvjawfkbrmntthqzjhgslbywpneuozwgkwchfmaulkynqudkuhnsjstdqstunywokwycnsnbsxutqyvxovyhppelvkuwjbjbezcsdqbmizdpajjjgdibsfwqktthqjvednpvkwquvriptvbrglbgcuvtbaapwobfgcqfjmvkqyjbqmvhzsoolmieuwoecfucbuhiunwssbwworxcftlodycypxkfdmyqbqmfikscxrblkwzsqetbgrpyownfjfxghdwzvugzkyqtcmnpqrvmuprcairzcrdwmzysqcoubmorxystaempaswnqfecudgotxnrbdbgainmnkydjmazsfkclvsdfcjyxjlgdstfjgnqnomayeytpmewcdqpxrdyzfhlsqnxlueoaavcmnmrbkvwykilumtkhylufzkjsbbkkoxsrgenejvuhjktrphdxarfzjgrdlphlwayszoaxtisugmmfqehgkdvllujynwwxxxtwjojdoimfxdppiwardxupgburtdfjmngmayzockauxyhvjnwxaumbfeztryxujzuslmazasvkuicaejcnpspuptpaspvqddbfovmpvvaemtvpnuyhmlkhywofwhzddfsecajtwkiqevdsjtbkwmxyqqxvpbanjvcktyqyudikeejcszysqhswtwnppsyqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxcqftcvlhchduydkcxhnsznvpyqpldprkyelkyljdzamjvncnkrmgtjnmuvticnnqzinyocitvvuypijrlwinfevnnnlvexwbasudktceqrzkgqbmwoizcszazufaedzeeoabsrbkkptypdjvkzdxdxlakggqwpkuysgxeoxgxfckrwcygxzksgrozzhvgxiahieufphqjcltpspnsrgoqheiiiahdppthzyhngghjaiycgnilppuggsvbkokmecdrpthqgiuetuifnhocmlnbkgsrzexmcqimwbhkukbdrlwwpnqdzprmiwaycguvadcibhwoynrpktmzizgaxsiwftuvrpkhamwmhzxmhytqtwousfilqecprrzvgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzegffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxffsnmfytmlcxzkotvgwm";
		String pattern = "gffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcx";
		
		int[] prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		System.out.println(isMatch(text, pattern, prefixArray));
		
		text = "BACBACBAD";
		pattern = "BACBAD";
		
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		System.out.println(isMatch(text, pattern, prefixArray));
		
		text = "ABCABCAABCABD";
		pattern = "ABCABD";
		
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		System.out.println(isMatch(text, pattern, prefixArray));
	}
	
	private static void checkNonOverlappingOccurrences() {
		String text = "gymkuurmyzcxkuzvoukuzjvsqujabigfqneoigwmjgrgdjemtwmhrrfjqyrsiufjnvqqnorcbghkhtfbaggjohnuzdewpwmjrjxctkhefhctswvrswhktovduurngvbxxncmvnqjcdhgbzeichyzshflehqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxcxkzjuopderzdutjihppcxeuhreyqtroaqxkujwpxqizzapmedahkodtiyrxexrtcnuijgblutlwljprraanlqaeahlmhtxkhwayvpoaqnfwiblrcruwlfrdeangvrhnpmsfmcqrengcrleojmmzjohibdeljdbkrvzfuoodrsdqdevgkmmwrqnzodmkjaqoalhampazfgrqrmajpiejyjidwenrhxikfoplcyxfpdgqdbkhqiynfpgvswomhbbqvtsztvduijwoapzbfbqymajebnvnqjloheenzprirzysvkvytllwcnhtwswwucnaezwcwpgytzndnjfrdgjodtahsppogkvrcxemxpyadewizdkedstprsfzzdqittrgwfnzdyivoaryekyrtftgwnrdfcakisnhonfiswepdbuadbuhbfljfjgowanijcmvhpmvwirgdqxgjgcijxovkwwfsscxndasvihsxqtfiyprsyygbfzbzgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxsorjkvenfldcbmfwtugryminvoapqekncgchbyiuzqbqoxksgxgxaothpfgxwjnumekutxkomghpiejhscwsssjiwdlyvleqotxwlwtekrlbtmtjlkulqiuznsslmskzwmthekziusbmounzzmeymiywjsyyiukirxqxdjahauxqqqjarhjbydedgyqguljdyutrvswbbrwpfvcuquouroixjraqgwghtrwqvasixlvsfclxlhkztomdljhnuejaotwmzerkohpchcnsgkgvwpizqupujjkokacynxhnkyupmzdgxonpelpqcqqbtebxlzfowzbepibehqhcblgylyobikkzhqmdhywkvejegomuhipznjyuxvdahzyqxjnllqfuynnrpnocsebnsiguwflptqszgaqtyinwxcdmkbizpptldqxbkfipxjggjstticpcqloelzpganmeljyzisbeajyjjsanzbyumrcolrgusokfvsprmzekedbouqhgclbncosroblquvgbdkoigcddkkhpzfktaudrmoqffzloynwyhhvwkcsnmcuuhfyxskavnhgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxhlcncjikekmgssesxvvsggaeovqsqjuvblqpeycwsrwzsxkslupcnjgvgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxagncuwkppvahjzxikhbzzdnphlyavcmlwjwxaovkvhsnuoriyybbkanmfuctbvrjlgblzwzyqizvjuuvpaqsagzhzlczoealmyimoebfywqoqwmgmwttaaiadvtznfgiqsrseumdeqwrzefxgbffrnbvuizaogtphnpwlqthuqmobogfuynaetvtxtaumiulirksfozwpylabefepjlcrsoavrwqkvnchcljxjxwncgspdydzwmugdpwhfwkfoyrryrljngdxboqmtumtnkztwfvpxyekknbvkabfjfohakfxocyhnrlyxaudfpxnkxrwgllibfehburfbynktvihwjyqcjyzviytsmhrydgrecjggouythplvedfughhpjjiuijenhpqvdzorjppjlesbelzhpfuwbhgjlfxteiklcbmbsqmujxyaqpmkeiwfvaotamqwfgtsdtfqxpxyanpfhlfnfisgrpvuszeokyixazoakcmvskzknzwkhkzezqrhulslhzicvuelwzsdvwkeheqalwrrcnniwrpgwvdhkvubcakejplyiphkulkunubwzsntjdijsucdqinkkztgwehhozgmfaurzucublkptolbqfkkylkvsftspsezjpucdqwcbqbowbxmgzpswqwbkzzgugyzqtixyjsgiihyebvaqpbnnrzcnuigrpgvcyuaijqnthvuenklcztomxcdshvpyjgqiojoubjadbikgjlmadwztduvdftdtcpcjqoxbidoplreqdzplnuxzhsnprbncathnfwbexytyodseyowxiqhmyowfykorisafwkhhkyxnosnbcuftgfzyghddmdztpujgqcgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxpremsvwasrtpbfbmypxvubcsqvwjloqkbxtruifcqxxatlrtcbklazaockvvvmvzhfudkvrmuyqyfmmxdjvlgofrynahohnldamqutdbmqjicvmrllwemskxyywfihioiekcsdqbcnjjzamgjeumhrlyqqctghnyrqsuwimgisafhaiegqaigcsmucobavhofearybogkcngzxnvdlfgauarbzldrfbqhbtzczqqbponpblmegibnrcxqlnbmnsuvftiixtffbblvllbdqgxssjufvbtimtsgejuqxuhckiwdnoqiblavhyqhthvrouimoqwrhbfsgzcovnqnhseqnvlogugcbpnfdebzyjxjcbcerrchzyfhmmhsvbhaqdjjvbbjecrsphqpxdslqajnwwnjyczyghyblnpkjipzfksuwuqnznrvevtprdaujkzmebqopzjtzujihcrijobecoafsxbvydbcdkjdcvxshqxdrsqjpeifihfscxnputeooxsqguxurtnsteluwaxidxpbhierghgpsomreayrkogelearyxrabdjyutemvxvletmczqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxyvlgvupcjlzmjnpevdiwywuifdidkqrshtokabnrbloqmfwxyyuagjbvbavamkjeudvmjpcculwmzwifygmwhietnrnstebqjigdrrszlgrqkuoeuonrxitwxhwmpeafspcxomyragzzwlchilokdszepvpufpfrvlmtltbgzfmmrkytmwwpprgyjwajhnslapltjbwiplpkknjukdiplcnuznwoftrjajzpyoncuwahwwtrkvnjttqzxognyavnvbfpfzgprnsrcejrrjazjzmarwysaszbtemyqkyzbdgnwgucbolpkaviumvhmgomuzvpgjntwatazrihbihrjyskbqzxzvbdiocuzhmexhytgzmhbymulccgpyixbiiulvcuryqkdfmukpivatkttigrakghaandvemwvsfkjnnhwvfhujwypbyvoeyyqvhnsixeavhfxzkmvyxqlrzrpggdruzrbaempklpfbccclndtwfymrcagmyqtscosdyfbbgiivxdeaszdxjuwtiwrydttleloutzphauktuankzxjzjkzyumnclsnhvyoaaygjviwviicfkjlyzqenhsqoormlgkwrrzktlwvkgywzxejvsqgjkclivzhrvxtkoptgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxxmexueodqkcavkslbcqvnozexlelqulkamxwwuqmvpaamedaehscqgftludhvrzgjuwubejxgltlbafvpfzswlfkbwsizpmoexnnnlsfevaenyzhvworgurhceoihxxatskuenqlyvagtxnesvimjstoqibfarlywmvlfcamdjccrzirjplxqkgpgjmoykdjlfmqwuzyzaueucznofixxwbymtningbimjjahfagtrcllqwdtanhssuomjdgowqptbqsnzylmxvzwxetaprgrhqgfkdssfrefhtsmmhpgghbwjoqducsjjznwjnsokhmtiecqeutfeolypttalhyvppoeyxrvmrcwlbukgydgbvswrezlhpuvqahoievwiwrvpfrbklnonuabafwdncnuvkromaagheqvlmhvepksdjvosalouiingzzpmczyhmmkellcauqaiubuvxnrclcuqaoezmuyhmwauuxfezethtfrpsirdedwctgbqpxtrcgagalgejuiiagapkvxpmizseyypsjltnuzgnpvjawfkbrmntthqzjhgslbywpneuozwgkwchfmaulkynqudkuhnsjstdqstunywokwycnsnbsxutqyvxovyhppelvkuwjbjbezcsdqbmizdpajjjgdibsfwqktthqjvednpvkwquvriptvbrglbgcuvtbaapwobfgcqfjmvkqyjbqmvhzsoolmieuwoecfucbuhiunwssbwworxcftlodycypxkfdmyqbqmfikscxrblkwzsqetbgrpyownfjfxghdwzvugzkyqtcmnpqrvmuprcairzcrdwmzysqcoubmorxystaempaswnqfecudgotxnrbdbgainmnkydjmazsfkclvsdfcjyxjlgdstfjgnqnomayeytpmewcdqpxrdyzfhlsqnxlueoaavcmnmrbkvwykilumtkhylufzkjsbbkkoxsrgenejvuhjktrphdxarfzjgrdlphlwayszoaxtisugmmfqehgkdvllujynwwxxxtwjojdoimfxdppiwardxupgburtdfjmngmayzockauxyhvjnwxaumbfeztryxujzuslmazasvkuicaejcnpspuptpaspvqddbfovmpvvaemtvpnuyhmlkhywofwhzddfsecajtwkiqevdsjtbkwmxyqqxvpbanjvcktyqyudikeejcszysqhswtwnppsyqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxcqftcvlhchduydkcxhnsznvpyqpldprkyelkyljdzamjvncnkrmgtjnmuvticnnqzinyocitvvuypijrlwinfevnnnlvexwbasudktceqrzkgqbmwoizcszazufaedzeeoabsrbkkptypdjvkzdxdxlakggqwpkuysgxeoxgxfckrwcygxzksgrozzhvgxiahieufphqjcltpspnsrgoqheiiiahdppthzyhngghjaiycgnilppuggsvbkokmecdrpthqgiuetuifnhocmlnbkgsrzexmcqimwbhkukbdrlwwpnqdzprmiwaycguvadcibhwoynrpktmzizgaxsiwftuvrpkhamwmhzxmhytqtwousfilqecprrzvgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzegffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxffsnmfytmlcxzkotvgwm";
		String pattern = "gffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcx";
		
		int[] prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		
		text = "BACBACBAD";
		pattern = "BACBAD";
		
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		
		text = "ABCABCAABCABD";
		pattern = "ABCABD";
		
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		
		text = "ABACABAD";
		pattern = "ABA";
		
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		
		text = "ABAABA";
		pattern = "ABA";
		
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		
		text = "ababababa";
		pattern = "aba";
		
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
	}
	
	private static void checkKMPSearch() {
		String text = "gymkuurmyzcxkuzvoukuzjvsqujabigfqneoigwmjgrgdjemtwmhrrfjqyrsiufjnvqqnorcbghkhtfbaggjohnuzdewpwmjrjxctkhefhctswvrswhktovduurngvbxxncmvnqjcdhgbzeichyzshflehqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxcxkzjuopderzdutjihppcxeuhreyqtroaqxkujwpxqizzapmedahkodtiyrxexrtcnuijgblutlwljprraanlqaeahlmhtxkhwayvpoaqnfwiblrcruwlfrdeangvrhnpmsfmcqrengcrleojmmzjohibdeljdbkrvzfuoodrsdqdevgkmmwrqnzodmkjaqoalhampazfgrqrmajpiejyjidwenrhxikfoplcyxfpdgqdbkhqiynfpgvswomhbbqvtsztvduijwoapzbfbqymajebnvnqjloheenzprirzysvkvytllwcnhtwswwucnaezwcwpgytzndnjfrdgjodtahsppogkvrcxemxpyadewizdkedstprsfzzdqittrgwfnzdyivoaryekyrtftgwnrdfcakisnhonfiswepdbuadbuhbfljfjgowanijcmvhpmvwirgdqxgjgcijxovkwwfsscxndasvihsxqtfiyprsyygbfzbzgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxsorjkvenfldcbmfwtugryminvoapqekncgchbyiuzqbqoxksgxgxaothpfgxwjnumekutxkomghpiejhscwsssjiwdlyvleqotxwlwtekrlbtmtjlkulqiuznsslmskzwmthekziusbmounzzmeymiywjsyyiukirxqxdjahauxqqqjarhjbydedgyqguljdyutrvswbbrwpfvcuquouroixjraqgwghtrwqvasixlvsfclxlhkztomdljhnuejaotwmzerkohpchcnsgkgvwpizqupujjkokacynxhnkyupmzdgxonpelpqcqqbtebxlzfowzbepibehqhcblgylyobikkzhqmdhywkvejegomuhipznjyuxvdahzyqxjnllqfuynnrpnocsebnsiguwflptqszgaqtyinwxcdmkbizpptldqxbkfipxjggjstticpcqloelzpganmeljyzisbeajyjjsanzbyumrcolrgusokfvsprmzekedbouqhgclbncosroblquvgbdkoigcddkkhpzfktaudrmoqffzloynwyhhvwkcsnmcuuhfyxskavnhgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxhlcncjikekmgssesxvvsggaeovqsqjuvblqpeycwsrwzsxkslupcnjgvgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxagncuwkppvahjzxikhbzzdnphlyavcmlwjwxaovkvhsnuoriyybbkanmfuctbvrjlgblzwzyqizvjuuvpaqsagzhzlczoealmyimoebfywqoqwmgmwttaaiadvtznfgiqsrseumdeqwrzefxgbffrnbvuizaogtphnpwlqthuqmobogfuynaetvtxtaumiulirksfozwpylabefepjlcrsoavrwqkvnchcljxjxwncgspdydzwmugdpwhfwkfoyrryrljngdxboqmtumtnkztwfvpxyekknbvkabfjfohakfxocyhnrlyxaudfpxnkxrwgllibfehburfbynktvihwjyqcjyzviytsmhrydgrecjggouythplvedfughhpjjiuijenhpqvdzorjppjlesbelzhpfuwbhgjlfxteiklcbmbsqmujxyaqpmkeiwfvaotamqwfgtsdtfqxpxyanpfhlfnfisgrpvuszeokyixazoakcmvskzknzwkhkzezqrhulslhzicvuelwzsdvwkeheqalwrrcnniwrpgwvdhkvubcakejplyiphkulkunubwzsntjdijsucdqinkkztgwehhozgmfaurzucublkptolbqfkkylkvsftspsezjpucdqwcbqbowbxmgzpswqwbkzzgugyzqtixyjsgiihyebvaqpbnnrzcnuigrpgvcyuaijqnthvuenklcztomxcdshvpyjgqiojoubjadbikgjlmadwztduvdftdtcpcjqoxbidoplreqdzplnuxzhsnprbncathnfwbexytyodseyowxiqhmyowfykorisafwkhhkyxnosnbcuftgfzyghddmdztpujgqcgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxpremsvwasrtpbfbmypxvubcsqvwjloqkbxtruifcqxxatlrtcbklazaockvvvmvzhfudkvrmuyqyfmmxdjvlgofrynahohnldamqutdbmqjicvmrllwemskxyywfihioiekcsdqbcnjjzamgjeumhrlyqqctghnyrqsuwimgisafhaiegqaigcsmucobavhofearybogkcngzxnvdlfgauarbzldrfbqhbtzczqqbponpblmegibnrcxqlnbmnsuvftiixtffbblvllbdqgxssjufvbtimtsgejuqxuhckiwdnoqiblavhyqhthvrouimoqwrhbfsgzcovnqnhseqnvlogugcbpnfdebzyjxjcbcerrchzyfhmmhsvbhaqdjjvbbjecrsphqpxdslqajnwwnjyczyghyblnpkjipzfksuwuqnznrvevtprdaujkzmebqopzjtzujihcrijobecoafsxbvydbcdkjdcvxshqxdrsqjpeifihfscxnputeooxsqguxurtnsteluwaxidxpbhierghgpsomreayrkogelearyxrabdjyutemvxvletmczqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxyvlgvupcjlzmjnpevdiwywuifdidkqrshtokabnrbloqmfwxyyuagjbvbavamkjeudvmjpcculwmzwifygmwhietnrnstebqjigdrrszlgrqkuoeuonrxitwxhwmpeafspcxomyragzzwlchilokdszepvpufpfrvlmtltbgzfmmrkytmwwpprgyjwajhnslapltjbwiplpkknjukdiplcnuznwoftrjajzpyoncuwahwwtrkvnjttqzxognyavnvbfpfzgprnsrcejrrjazjzmarwysaszbtemyqkyzbdgnwgucbolpkaviumvhmgomuzvpgjntwatazrihbihrjyskbqzxzvbdiocuzhmexhytgzmhbymulccgpyixbiiulvcuryqkdfmukpivatkttigrakghaandvemwvsfkjnnhwvfhujwypbyvoeyyqvhnsixeavhfxzkmvyxqlrzrpggdruzrbaempklpfbccclndtwfymrcagmyqtscosdyfbbgiivxdeaszdxjuwtiwrydttleloutzphauktuankzxjzjkzyumnclsnhvyoaaygjviwviicfkjlyzqenhsqoormlgkwrrzktlwvkgywzxejvsqgjkclivzhrvxtkoptgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxxmexueodqkcavkslbcqvnozexlelqulkamxwwuqmvpaamedaehscqgftludhvrzgjuwubejxgltlbafvpfzswlfkbwsizpmoexnnnlsfevaenyzhvworgurhceoihxxatskuenqlyvagtxnesvimjstoqibfarlywmvlfcamdjccrzirjplxqkgpgjmoykdjlfmqwuzyzaueucznofixxwbymtningbimjjahfagtrcllqwdtanhssuomjdgowqptbqsnzylmxvzwxetaprgrhqgfkdssfrefhtsmmhpgghbwjoqducsjjznwjnsokhmtiecqeutfeolypttalhyvppoeyxrvmrcwlbukgydgbvswrezlhpuvqahoievwiwrvpfrbklnonuabafwdncnuvkromaagheqvlmhvepksdjvosalouiingzzpmczyhmmkellcauqaiubuvxnrclcuqaoezmuyhmwauuxfezethtfrpsirdedwctgbqpxtrcgagalgejuiiagapkvxpmizseyypsjltnuzgnpvjawfkbrmntthqzjhgslbywpneuozwgkwchfmaulkynqudkuhnsjstdqstunywokwycnsnbsxutqyvxovyhppelvkuwjbjbezcsdqbmizdpajjjgdibsfwqktthqjvednpvkwquvriptvbrglbgcuvtbaapwobfgcqfjmvkqyjbqmvhzsoolmieuwoecfucbuhiunwssbwworxcftlodycypxkfdmyqbqmfikscxrblkwzsqetbgrpyownfjfxghdwzvugzkyqtcmnpqrvmuprcairzcrdwmzysqcoubmorxystaempaswnqfecudgotxnrbdbgainmnkydjmazsfkclvsdfcjyxjlgdstfjgnqnomayeytpmewcdqpxrdyzfhlsqnxlueoaavcmnmrbkvwykilumtkhylufzkjsbbkkoxsrgenejvuhjktrphdxarfzjgrdlphlwayszoaxtisugmmfqehgkdvllujynwwxxxtwjojdoimfxdppiwardxupgburtdfjmngmayzockauxyhvjnwxaumbfeztryxujzuslmazasvkuicaejcnpspuptpaspvqddbfovmpvvaemtvpnuyhmlkhywofwhzddfsecajtwkiqevdsjtbkwmxyqqxvpbanjvcktyqyudikeejcszysqhswtwnppsyqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxcqftcvlhchduydkcxhnsznvpyqpldprkyelkyljdzamjvncnkrmgtjnmuvticnnqzinyocitvvuypijrlwinfevnnnlvexwbasudktceqrzkgqbmwoizcszazufaedzeeoabsrbkkptypdjvkzdxdxlakggqwpkuysgxeoxgxfckrwcygxzksgrozzhvgxiahieufphqjcltpspnsrgoqheiiiahdppthzyhngghjaiycgnilppuggsvbkokmecdrpthqgiuetuifnhocmlnbkgsrzexmcqimwbhkukbdrlwwpnqdzprmiwaycguvadcibhwoynrpktmzizgaxsiwftuvrpkhamwmhzxmhytqtwousfilqecprrzvgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzegffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxffsnmfytmlcxzkotvgwm";
		String pattern = "gffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcx";
		
		printList(KMPSearch(text, pattern));
		
		text = "BACBACBAD";
		pattern = "BACBAD";
		
		printList(KMPSearch(text, pattern));
		
		text = "ABCABCAABCABD";
		pattern = "ABCABD";
		
		printList(KMPSearch(text, pattern));
		
		text = "ABACABAD";
		pattern = "ABA";
		
		printList(KMPSearch(text, pattern));
		
		text = "ABAABA";
		pattern = "ABA";
		
		printList(KMPSearch(text, pattern));
		
		text = "ababababa";
		pattern = "aba";
		
		printList(KMPSearch(text, pattern));
	}
	
	public static void main(String[] args) {
//		checkIsMatchFunction();
//		checkNonOverlappingOccurrences();
//		checkKMPSearch();
		
		String text = "AkIRltGuHudGcXAtXcNwtkQibnCEkSJuZKCYUHZdwzcsINGhnGOPtbWoZyaLfxxvwxhpEJyrdWHlbwESHweBiuIRFHRpZpLHBKevdaAWszpmtWZKLvhPZwWPGEwgPsDuOkHFgLUwRWWKANQQaqPvJSYVvAcalDdFkMoEoyCymPKLywZjACMDTNUxOQaiyAFEStjNQNJaAadeLcRuEDhGreVkoBsAETPNADQdLtfIVdOeCvzNHVKAUBkPSyxoEETNdEWUszkWdvbbJXtktfVbqPebYuTSuWPxvpszmkuvayENCwWAkIimcVCaZRivueSrWdcqzTKgjOxZCOVRiaQDjRIHHlMQfWDIAUacYGnQVNTWslfiOcAxlfZJBQmJxhJXHlSKcKoGrwCaTUiyJhibxAsxdLOwEamPaiTSvczfTzsgWXnIjydTOcrnGipLSoYhXOUBGfjJfzWsoRWlDxWisJIiLbYYpTUhlFsNhjGAgiLGZkigPZtOpCAKSiGipVuRVYfuSHEiRKTxIEQAvHopSwcRoeKIoWGQQywTtLFjerQVTFqWipakbHClUBINidzbaexZKHRlxPZooKIVSRyZwYKIPxxSqGxCfsDTmQeUmaYadjwZtXHSpJcaFiyYUFkvdxoveXGuTqMKYBprEoOldQJXIBEYcwNMCVSNkfAtjBnZSdFeFkUMDgecdKWsDfiYPbhbBYkVbXhtBAyQVfKjDGSHhLmbzKFibeomLJSOxNchqOzdGMXldfgNCumxc";
		text = "NHbciZalJralstQcmyrDVLZmRKExkmEIvGcVqKzIHMIVxvvsgKyRYZspzpaFuhriNwuqapnGLdnCZXmvoxlMGdZLRgYwtWLfUFNIEVVOrHQuJAfIFRqSXEwtxXzGNBlnmkxcBSyJdOFHBWTQGBvUSYGGfmMJTxPioeOHfGesmddHPcEnjQAZhnAbhDkqLAGDAjwpJXXwDCkGBFvHwUTJyNMnyOsJZPMJriWQRPPWelOmEQnSWBFIMBbhcoygbJPODMrhpuZQqcMDseqylGNiqGKtPEEhdPslyDdFXEDlgVFmTiBMpGINxviCAGwkbkIchvyzEDZiNHIXlNmtyLOiWqQKDIUSMzKZMPwNQICykawDsNGysviHupvloZpyyCDmdiFyhHnOOSWfZHMiUYXezKBrYakLANmKjlCPMjWwoaLhNIoSPeYcRhFpfKtFeLJbnGMdPCAHkejwfsMoTtfmSfhsVSGZmOBIoRQjIXktGIKusywtVBMwMAfpPSoHVgSKcPwVOlsWmxsFauMNKqoGhRlzbMczpGldPHflqDXdDmtHrFZhgbEaVMOlXwPeFFNNGBxOoTlNmGJIzOSnl";
		text = "asasasrterer";
	//	text = "evXqKymsYGxDmAMQBvIGHUIqChblgaEyVouHVPYQuXjmEzPAibSNLcNJBPNihoVFKUCdCfeTqKfLltYPZNNCBDCCiglCkBCqwXjdnqLDirUqsKjhFrmNqTpJdsXPvyiejDfwkMStLFwAoDLPwHbnzWjDFoSIvSrzKhlvniFOorSxzrFhuwIsbmsEBTvNavcbdJupaCJzZAwCYAFlshxrpMstvOfXaXNuFeCYqVEyfwwvFuSggqUKFJRNUGJAFOtxsJsdNtvTUywrRJLZMCNxSGnoQiMCIjqYlAKCrDgQgwjJyviNNbOXVWxWVRsEquXTRkMpxEAiRAEyCymKOSqkBBcuinboYXYdQsOOLkNPXmXQbUSSYWfYfTBSLdKJwQfYjovfFfQrTtYOesFZRJmSfmucvwceWiRAUhZdgkAhOyhCBbKNLMfhzODUFbYoldQzCDggiBHtUeYvAibMiDkaHWqjexIvvdOTczsxvfXtpjzeqBpoJpvKQOjenfzrlEnMWPAhEUbZykGuNhVSMipaeKvcsapHwbhlflVDNmhxFRkEHljswpebGorUFZyaIJiMlxEjLrT";
				
		Set<String> set1 = distinctSubstring(text);
		Set<String> set2 = numberOfUniqueSubstrings(text);
		
		System.out.println(set1.size() + " " + set2.size());
		
		set1.removeAll(set2);
		
		for (String str : set1) {
			System.out.println(str);
		}
	}
}
