package name.nicholasgribanov.recipe.services;

import name.nicholasgribanov.recipe.commands.UnitOfMeasureCommand;
import name.nicholasgribanov.recipe.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService  {
    Set<UnitOfMeasureCommand> findAllUom();
}
