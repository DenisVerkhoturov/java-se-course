According to this data:
```java
class MedicalStaff {}
class Doctor extends MedicalStaff {}
class Nurse extends MedicalStaff {}
class HeadDoctor extends Doctor {}
```

| Assignment | Is correct | Explanation |
|:-----------|:----------:|:------------|
| `Doctor doctor = new Doctor();`       | :heavy_plus_sign:  | Direct assignment |
| `Doctor doctor = new MedicalStaff();` | :heavy_minus_sign: | Unable to assign object of superclass to subclass reference |
| `Doctor doctor = new HeadDoctor();`   | :heavy_plus_sign:  | Any object can be assigned to its superclasses |
| `Object object = new HeadDoctor();`   | :heavy_plus_sign:  | Every object in java implicitly extends `Object` |
| `HeadDoctor doctor = new Object();`   | :heavy_minus_sign: | `Object` is not subclass of `HeadDoctor` or `HeadDoctor` itself |
| `Doctor doctor = new Nurse();`        | :heavy_minus_sign: | `Nurse` is not subclass of `Doctor` or `Doctor` itself |
| `Nurse nurse = new Doctor();`         | :heavy_minus_sign: | `Doctor` is not subclass of `Nurse` or `Nurse` itself |
| `Object object = new Nurse();`        | :heavy_plus_sign:  | Every object in java implicitly extends `Object` |
| `List<Doctor> doctors = new ArrayList<Doctor>();`       | :heavy_plus_sign:  | Working with `ArrayList<Doctor>` by interface `List<Doctor>` |
| `List<MedicalStaff> doctors = new ArrayList<Doctor>();` | :heavy_minus_sign: | `ArrayList<Doctor>` is not a subtype of `List<MedicalStaff>` event though `Doctor` is subtype of `MedicalStaff` |
| `List<Doctor> doctors = new ArrayList<MedicalStaff>();` | :heavy_minus_sign: | `ArrayList<MedicalStaff>` is not a subtype of `List<Doctor>` |
| `List<Object> doctors = new ArrayList<Doctor>();`       | :heavy_minus_sign: | `ArrayList<MedicalStaff>` is not a subtype of `List<Doctor>` event though `Doctor` is subtype of `Object` |
| `List<Object> doctors = new ArrayList<Object>();`       | :heavy_plus_sign:  | Working with `ArrayList<Object>` by interface `List<Object>` |
