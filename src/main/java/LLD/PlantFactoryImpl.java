package LLD;

public class PlantFactoryImpl implements PlantFactory{

    @Override
    public Plant createPlant(String params) {
    return new Plant();
    }
}
