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
	
	private static List<Integer> occurences(String text, String pattern, int[] prefixArray) {
		List<Integer> occurencesList = new ArrayList<>();
		
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
				j = prefixArray[j - 1];
			}
		}
		
		return occurencesList;
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
	
	public static void main(String[] args) {
		
		String text = "gymkuurmyzcxkuzvoukuzjvsqujabigfqneoigwmjgrgdjemtwmhrrfjqyrsiufjnvqqnorcbghkhtfbaggjohnuzdewpwmjrjxctkhefhctswvrswhktovduurngvbxxncmvnqjcdhgbzeichyzshflehqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxcxkzjuopderzdutjihppcxeuhreyqtroaqxkujwpxqizzapmedahkodtiyrxexrtcnuijgblutlwljprraanlqaeahlmhtxkhwayvpoaqnfwiblrcruwlfrdeangvrhnpmsfmcqrengcrleojmmzjohibdeljdbkrvzfuoodrsdqdevgkmmwrqnzodmkjaqoalhampazfgrqrmajpiejyjidwenrhxikfoplcyxfpdgqdbkhqiynfpgvswomhbbqvtsztvduijwoapzbfbqymajebnvnqjloheenzprirzysvkvytllwcnhtwswwucnaezwcwpgytzndnjfrdgjodtahsppogkvrcxemxpyadewizdkedstprsfzzdqittrgwfnzdyivoaryekyrtftgwnrdfcakisnhonfiswepdbuadbuhbfljfjgowanijcmvhpmvwirgdqxgjgcijxovkwwfsscxndasvihsxqtfiyprsyygbfzbzgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxsorjkvenfldcbmfwtugryminvoapqekncgchbyiuzqbqoxksgxgxaothpfgxwjnumekutxkomghpiejhscwsssjiwdlyvleqotxwlwtekrlbtmtjlkulqiuznsslmskzwmthekziusbmounzzmeymiywjsyyiukirxqxdjahauxqqqjarhjbydedgyqguljdyutrvswbbrwpfvcuquouroixjraqgwghtrwqvasixlvsfclxlhkztomdljhnuejaotwmzerkohpchcnsgkgvwpizqupujjkokacynxhnkyupmzdgxonpelpqcqqbtebxlzfowzbepibehqhcblgylyobikkzhqmdhywkvejegomuhipznjyuxvdahzyqxjnllqfuynnrpnocsebnsiguwflptqszgaqtyinwxcdmkbizpptldqxbkfipxjggjstticpcqloelzpganmeljyzisbeajyjjsanzbyumrcolrgusokfvsprmzekedbouqhgclbncosroblquvgbdkoigcddkkhpzfktaudrmoqffzloynwyhhvwkcsnmcuuhfyxskavnhgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxhlcncjikekmgssesxvvsggaeovqsqjuvblqpeycwsrwzsxkslupcnjgvgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxagncuwkppvahjzxikhbzzdnphlyavcmlwjwxaovkvhsnuoriyybbkanmfuctbvrjlgblzwzyqizvjuuvpaqsagzhzlczoealmyimoebfywqoqwmgmwttaaiadvtznfgiqsrseumdeqwrzefxgbffrnbvuizaogtphnpwlqthuqmobogfuynaetvtxtaumiulirksfozwpylabefepjlcrsoavrwqkvnchcljxjxwncgspdydzwmugdpwhfwkfoyrryrljngdxboqmtumtnkztwfvpxyekknbvkabfjfohakfxocyhnrlyxaudfpxnkxrwgllibfehburfbynktvihwjyqcjyzviytsmhrydgrecjggouythplvedfughhpjjiuijenhpqvdzorjppjlesbelzhpfuwbhgjlfxteiklcbmbsqmujxyaqpmkeiwfvaotamqwfgtsdtfqxpxyanpfhlfnfisgrpvuszeokyixazoakcmvskzknzwkhkzezqrhulslhzicvuelwzsdvwkeheqalwrrcnniwrpgwvdhkvubcakejplyiphkulkunubwzsntjdijsucdqinkkztgwehhozgmfaurzucublkptolbqfkkylkvsftspsezjpucdqwcbqbowbxmgzpswqwbkzzgugyzqtixyjsgiihyebvaqpbnnrzcnuigrpgvcyuaijqnthvuenklcztomxcdshvpyjgqiojoubjadbikgjlmadwztduvdftdtcpcjqoxbidoplreqdzplnuxzhsnprbncathnfwbexytyodseyowxiqhmyowfykorisafwkhhkyxnosnbcuftgfzyghddmdztpujgqcgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxpremsvwasrtpbfbmypxvubcsqvwjloqkbxtruifcqxxatlrtcbklazaockvvvmvzhfudkvrmuyqyfmmxdjvlgofrynahohnldamqutdbmqjicvmrllwemskxyywfihioiekcsdqbcnjjzamgjeumhrlyqqctghnyrqsuwimgisafhaiegqaigcsmucobavhofearybogkcngzxnvdlfgauarbzldrfbqhbtzczqqbponpblmegibnrcxqlnbmnsuvftiixtffbblvllbdqgxssjufvbtimtsgejuqxuhckiwdnoqiblavhyqhthvrouimoqwrhbfsgzcovnqnhseqnvlogugcbpnfdebzyjxjcbcerrchzyfhmmhsvbhaqdjjvbbjecrsphqpxdslqajnwwnjyczyghyblnpkjipzfksuwuqnznrvevtprdaujkzmebqopzjtzujihcrijobecoafsxbvydbcdkjdcvxshqxdrsqjpeifihfscxnputeooxsqguxurtnsteluwaxidxpbhierghgpsomreayrkogelearyxrabdjyutemvxvletmczqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxyvlgvupcjlzmjnpevdiwywuifdidkqrshtokabnrbloqmfwxyyuagjbvbavamkjeudvmjpcculwmzwifygmwhietnrnstebqjigdrrszlgrqkuoeuonrxitwxhwmpeafspcxomyragzzwlchilokdszepvpufpfrvlmtltbgzfmmrkytmwwpprgyjwajhnslapltjbwiplpkknjukdiplcnuznwoftrjajzpyoncuwahwwtrkvnjttqzxognyavnvbfpfzgprnsrcejrrjazjzmarwysaszbtemyqkyzbdgnwgucbolpkaviumvhmgomuzvpgjntwatazrihbihrjyskbqzxzvbdiocuzhmexhytgzmhbymulccgpyixbiiulvcuryqkdfmukpivatkttigrakghaandvemwvsfkjnnhwvfhujwypbyvoeyyqvhnsixeavhfxzkmvyxqlrzrpggdruzrbaempklpfbccclndtwfymrcagmyqtscosdyfbbgiivxdeaszdxjuwtiwrydttleloutzphauktuankzxjzjkzyumnclsnhvyoaaygjviwviicfkjlyzqenhsqoormlgkwrrzktlwvkgywzxejvsqgjkclivzhrvxtkoptgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxxmexueodqkcavkslbcqvnozexlelqulkamxwwuqmvpaamedaehscqgftludhvrzgjuwubejxgltlbafvpfzswlfkbwsizpmoexnnnlsfevaenyzhvworgurhceoihxxatskuenqlyvagtxnesvimjstoqibfarlywmvlfcamdjccrzirjplxqkgpgjmoykdjlfmqwuzyzaueucznofixxwbymtningbimjjahfagtrcllqwdtanhssuomjdgowqptbqsnzylmxvzwxetaprgrhqgfkdssfrefhtsmmhpgghbwjoqducsjjznwjnsokhmtiecqeutfeolypttalhyvppoeyxrvmrcwlbukgydgbvswrezlhpuvqahoievwiwrvpfrbklnonuabafwdncnuvkromaagheqvlmhvepksdjvosalouiingzzpmczyhmmkellcauqaiubuvxnrclcuqaoezmuyhmwauuxfezethtfrpsirdedwctgbqpxtrcgagalgejuiiagapkvxpmizseyypsjltnuzgnpvjawfkbrmntthqzjhgslbywpneuozwgkwchfmaulkynqudkuhnsjstdqstunywokwycnsnbsxutqyvxovyhppelvkuwjbjbezcsdqbmizdpajjjgdibsfwqktthqjvednpvkwquvriptvbrglbgcuvtbaapwobfgcqfjmvkqyjbqmvhzsoolmieuwoecfucbuhiunwssbwworxcftlodycypxkfdmyqbqmfikscxrblkwzsqetbgrpyownfjfxghdwzvugzkyqtcmnpqrvmuprcairzcrdwmzysqcoubmorxystaempaswnqfecudgotxnrbdbgainmnkydjmazsfkclvsdfcjyxjlgdstfjgnqnomayeytpmewcdqpxrdyzfhlsqnxlueoaavcmnmrbkvwykilumtkhylufzkjsbbkkoxsrgenejvuhjktrphdxarfzjgrdlphlwayszoaxtisugmmfqehgkdvllujynwwxxxtwjojdoimfxdppiwardxupgburtdfjmngmayzockauxyhvjnwxaumbfeztryxujzuslmazasvkuicaejcnpspuptpaspvqddbfovmpvvaemtvpnuyhmlkhywofwhzddfsecajtwkiqevdsjtbkwmxyqqxvpbanjvcktyqyudikeejcszysqhswtwnppsyqgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxcqftcvlhchduydkcxhnsznvpyqpldprkyelkyljdzamjvncnkrmgtjnmuvticnnqzinyocitvvuypijrlwinfevnnnlvexwbasudktceqrzkgqbmwoizcszazufaedzeeoabsrbkkptypdjvkzdxdxlakggqwpkuysgxeoxgxfckrwcygxzksgrozzhvgxiahieufphqjcltpspnsrgoqheiiiahdppthzyhngghjaiycgnilppuggsvbkokmecdrpthqgiuetuifnhocmlnbkgsrzexmcqimwbhkukbdrlwwpnqdzprmiwaycguvadcibhwoynrpktmzizgaxsiwftuvrpkhamwmhzxmhytqtwousfilqecprrzvgffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzegffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcxffsnmfytmlcxzkotvgwm";
		String pattern = "gffmaqtamjwotjzukwqbnkysdngjjiddhhblhsagjqpjpchqiqjarjkwkkodbwombevkswdmoueblahvsnrsuvpoawniolwdqbciocjuehhyutatjcqqomqkcntmxvgfsfaqvgkunuonobqsrbiaptzbbbkuxiefxqsblfqedqxrrtgtlmpofcfzanzeffsnmfytmlcx";
		
		// calculate prefix function for the pattern
		int[] prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		System.out.println(isMatch(text, pattern, prefixArray));
		System.out.println("1st pattern");
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		
		text = "BACBACBAD";
		pattern = "BACBAD";
		
		// calculate prefix function for the pattern
		System.out.println("BACBACBAD" + "    " + pattern);
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		System.out.println(isMatch(text, pattern, prefixArray));
//		
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		printList(KMPSearch(text, pattern));
		
		text = "ABCABCAABCABD";
		pattern = "ABCABD";
		
		// calculate prefix function for the pattern
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		System.out.println(isMatch(text, pattern, prefixArray));
		printList(KMPSearch(text, pattern));
		System.out.println(text + "   " + pattern);
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		
		text = "ABACABAD";
		pattern = "ABA";
		
		// calculate prefix function for the pattern
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		System.out.println(text + "    " + pattern);
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		printList(KMPSearch(text, pattern));
		
		text = "ABAABA";
		pattern = "ABA";
		
		// calculate prefix function for the pattern
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		System.out.println(text + "   " + pattern);
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
		printList(KMPSearch(text, pattern));
		
		text = "ababababa";
		pattern = "aba";
		
		// calculate prefix function for the pattern
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		System.out.println(text + "   " + pattern);
		printList(nonOverlappingOccurences(text, pattern, prefixArray));
	}
}
