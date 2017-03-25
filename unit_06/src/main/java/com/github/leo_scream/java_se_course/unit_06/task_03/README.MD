# References on collections

According to this data:
```java
class MedicalStaff {}
class Doctor extends MedicalStaff {}
class Nurse extends MedicalStaff {}
class HeadDoctor extends Doctor {}
```

| Assignment                                              | Is correct | Explanation                                                                                                     |
|:--------------------------------------------------------|:----------:|:----------------------------------------------------------------------------------------------------------------|
| `Doctor doctor = new Doctor();`                         |  +         | Direct assignment                                                                                               |
| `Doctor doctor = new MedicalStaff();`                   |  -         | Unable to assign object of superclass to subclass reference                                                     |
| `Doctor doctor = new HeadDoctor();`                     |  +         | Any object can be assigned to its superclasses                                                                  |
| `Object object = new HeadDoctor();`                     |  +         | Every object in java implicitly extends `Object`                                                                |
| `HeadDoctor doctor = new Object();`                     |  -         | `Object` is not subclass of `HeadDoctor` or `HeadDoctor` itself                                                 |
| `Doctor doctor = new Nurse();`                          |  -         | `Nurse` is not subclass of `Doctor` or `Doctor` itself                                                          |
| `Nurse nurse = new Doctor();`                           |  -         | `Doctor` is not subclass of `Nurse` or `Nurse` itself                                                           |
| `Object object = new Nurse();`                          |  +         | Every object in java implicitly extends `Object`                                                                |
| `List<Doctor> doctors = new ArrayList<Doctor>();`       |  +         | Working with `ArrayList<Doctor>` by interface `List<Doctor>`                                                    |
| `List<MedicalStaff> doctors = new ArrayList<Doctor>();` |  -         | `ArrayList<Doctor>` is not a subtype of `List<MedicalStaff>` event though `Doctor` is subtype of `MedicalStaff` |
| `List<Doctor> doctors = new ArrayList<MedicalStaff>();` |  -         | `ArrayList<MedicalStaff>` is not a subtype of `List<Doctor>`                                                    |
| `List<Object> doctors = new ArrayList<Doctor>();`       |  -         | `ArrayList<MedicalStaff>` is not a subtype of `List<Doctor>` event though `Doctor` is subtype of `Object`       |
| `List<Object> doctors = new ArrayList<Object>();`       |  +         | Working with `ArrayList<Object>` by interface `List<Object>`                                                    |
