package com.pretz.dyntreeserver.generator;

import com.pretz.dyntreeserver.domain.DynCharacter;
import com.pretz.dyntreeserver.domain.DynTree;
import com.pretz.dyntreeserver.domain.Name;
import com.pretz.dyntreeserver.repository.NameListRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DynTreeGenerator {

    private final NameListRepo nameListRepo;

    @Autowired
    public DynTreeGenerator(NameListRepo nameListRepo) {
        this.nameListRepo = nameListRepo;
    }

    public DynTree generateDynTree(DynTreeInput dynTreeInput) {

        DynCharacter founder = new DynCharacter(dynTreeInput.getMainCharacterName(), dynTreeInput.getStartingYear());

        DynTree generatedTree = new DynTree(dynTreeInput.getFamilyName(), founder);

        int familyCount = dynTreeInput.getFamilyCount();
        int generationsCount = dynTreeInput.getGenerationsCount();
        double childrenPerCharacter = dynTreeInput.getChildrenPerCharacter();
        int maturityAge = dynTreeInput.getMaturityAge();

        List<String> nameList = getNameListById(dynTreeInput.getNameListId());

        LinkedList<DynCharacter> characterQueue = new LinkedList<>();
        characterQueue.add(founder);

        while (generatedTree.getFamilyCount() < familyCount) {
            DynCharacter parent = characterQueue.getFirst();
            while (parent.getChildrenCount() < childrenPerCharacter && generatedTree.getFamilyCount() < familyCount) {
                DynCharacter child = generateCharacter(nameList, parent, maturityAge);
                parent.addChild(child);
                characterQueue.add(child);
                generatedTree.addCharacter(child);
            }
            characterQueue.removeFirst();
        }

        return generatedTree;
    }

    private DynCharacter generateCharacter(List<String> nameList, DynCharacter parent, int maturityAge) {
        return new DynCharacter(createRandomNameFromNameList(nameList), createBirthDateBasingOnParentBirthDate(parent, maturityAge));
    }

    private List<String> getNameListById(Long id) {
        return nameListRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Unable to find name list with id " + id))
                .getNames()
                .stream()
                .map(Name::getName)
                .collect(Collectors.toList());
        /*List<String> nameList = new ArrayList<>();
        nameList.add("Donald");
        nameList.add("Eric");
        nameList.add("Adolf");
        nameList.add("Joseph");
        nameList.add("Randolph");

        return nameList;*/
    }

    private Year createBirthDateBasingOnParentBirthDate(DynCharacter parent, int maturityAge) {
        Year earliestPossibleBirthYear = parent.getBirthDate().plus(maturityAge, ChronoUnit.YEARS);
        return earliestPossibleBirthYear.plus(new Random().nextInt(15), ChronoUnit.YEARS);
    }

    private String createRandomNameFromNameList(List<String> nameList) {
        Collections.shuffle(nameList);
        return nameList.get(0);
    }
}
