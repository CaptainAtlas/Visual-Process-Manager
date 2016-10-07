###Tables for Part II

---

**No Business Requirements**

---



**User Requirements**

|   ID   | Requirement                                                                    | Topic Area         | Priority  |
|:------:|--------------------------------------------------------------------------------|--------------------|-----------|
| US-001 | I want to be able to review  my processes visually                             | Visualization      | Critical  |
| US-002 | I want to be able to manage my processes by interacting with the visualization | Process Management | High      |
| US-003 | I want to see a history of my usage                                            | Recording          | Medium    |
| US-004 | I want to be able to sort my processes by different metrics                    | Visualization      | Medium    |


**Functional Requirements**

| ID | Requirement | Topic Area | Priority |
| :---: | --- | --- | --- |
| FR-01 | Processes must be identified by PID | Process Management | Critical |
| FR-02 | Processes must be presented visually | Visualization | High |
| FR-03 | Visual process instances change appearance based on activity | Visualization | High |
| FR-04 | Processes can be managed by sending signals | Management | High |
| FR-05 | Processes can be sorted by category/metric | Management | Medium |
| FR-06 | Process data can be stored in a database | Recording | Medium |
| FR-07 | Database data can be shown over time | Visualization | Medium |
| FR-08 | I don't want to be able to kill the program with the program    | Management | Low      |



**Non-Functional Requirements**

|   ID   | Requirement                                                       | Topic Area    | Priority |
|:------:|-------------------------------------------------------------------|---------------|----------|
| NF-001 | I want the visualization to be sleek and intuitive                | Visualization | Low      |
| NF-002 | I want to be the only one that can access my data                 | Security      | Medium   |
| NF-004 | I want the color and locality of process nodes to be descriptive  | Visualization | Medium   |
| NF-005 | I want my data to be updated in reasonable time                   | Performance   | Low      |

