## Demo Pet Finder Application<br>
NOTICE: Document Work in Progress
### By <b>Jason Clair</b>
This project is to showcase my software engineering with focus on OOP, TDD, SDLC, and many other standard practices.

# Document History
| Date (yyyy-mm-dd) | Description |
|:-----------|:-------------------------------------:|
| 2025-10-11 | Updated general outline for README.md |

# Index
### Introduction
[Introduction](#introduction-bookmark)<br>
[Purpose of the Document](#purpose-bookmark)<br>
[Project Scope](#project-scope-bookmark)<br>
[Related Documents](#related-documents-bookmark)<br>
[Terms/Acronyms and Definitions](#terms-acronyms-definitions-bookmark)<br>
[Risks and Assumptions](#risks-and-assumptions-bookmark)<br>
### System Overview
[System Overview](#system-overview-bookmark)<br>
[Data Flow Diagram](#data-flow-diagram-bookmark)<br>
[Application Screen Flow](#flow-chart-bookmark)<br>
[Sitemap](#site-map-bookmark)<br>
### Functional Specifications
[Functional Specifications](#functional-specifications-bookmark)<br>
[Wireframes](#wireframes-bookamrk)<br>
[Functional Requirements](#functional-requirments-bookmark)<br>
[Nonfunctional Requirements](#nonfunctional-requirments-bookmark)<br>
### [Open Issues](#open-issues-bookmark)
### [References](#references-bookmark)
<br>

<!-- END OF INDEX - START OF CHAPTERS -->

<a id="introduction-bookmark"></a>
## Introduction
<a id="purpose-bookmark"></a>
### Purpose of the Document
The purpose of this document is to outline the design and implementation of the overall project. This project is a Full-Stack application consisting of Angular and Spring-Boot. Please use the index to view many of the projects content and review the code base. If you have any suggestions on how I can improve the project, please feel free to reach out to me. I'm always looking for ways to become a better engineer and leader.

<a id="project-scope-bookmark"></a>
### Project Scope
The Project Scope will outline the overall features and behaviors that will be implemented into the overall project
- Register and login to an account securely
- Provide location for Google API to locate
- Register a pet with details
- Provide push notification when in area of lost pet
- Make purchases for lost pet items i.e. GPS Collars

<a id="related-documents-bookmark"></a>
### Related Documents
- Jira: https://cst326fall2025.atlassian.net/jira/software/projects/PET/boards/34/backlog?atlOrigin=eyJpIjoiOWM5ZDNkMGRjNTc2NGNiZGFhNDBkM2E1NDQxMDkwNTMiLCJwIjoiaiJ9

<a id="terms-acronyms-definitions-bookmark"></a>
### Terms/Acronyms and Definitions
Here you can find all of the terms and acronyms that may not be common knowledge for those whom are not tanyard software developers.
<table>
    <thead>
        <tr>
            <th>Acronym</th>
            <th>Defined</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>SDLC</td>
            <td>Software Development Life Cycle</td>
            <td>The process pipeline on how software is designed, implemented and deployed.</td>
        </tr>
        <tr>
            <td>TDD</td>
            <td>Test Driven Design</td>
            <td>The process of writing the test code before implementing class methods.</td>
        </tr>
    </tbody>
</table>

<a id="risks-and-assumptions-bookmark"></a>
### Risks and Assumptions
With any project, there are risks and aspects that we must assume. Here I will outline the possible risks, the management and what we are going to assume during production.

- N/A

<a id="system-overview-bookmark"></a>
## System Overview
The system overview chapter will outline the technical aspects of the project. Here you will find logical concepts for the flow of data, page navigation, sitemaps and more.

<a id="data-flow-diagram-bookmark"></a>
### Data Flow Diagram

<a id="flow-chart-bookmark"></a>
### Flow Charts

<a id="site-map-bookmark"></a>
### Site Maps

## Functional Specifications

<a id="functional-specifications-bookmark"></a>
### Functional Specifications

<a id="wireframes-bookamrk"></a>
### Wireframes
All of the pages wireframes will have the same general nav-bar. There will be a HOME icon, as well as a link to the store page for making purchases for GPS collars and other items. The client will be able to look at their cart and on the far right will be able to login or register if they do not have an account.

![Home Page](./Resources/Images/Wireframes/Home.png)
The Home splash screen will have an immediate option to seek if there is a missing pet in their location as well as other possible items that might be on sale.

![Store Page](./Resources/Images/Wireframes/Store.png)
The Store page will allow any user to make a purchase of items that they might need for keeping their furry friend safe. Backlog on Jira might allow for other sorting functionality as well if time permits.

![Product Modal](./Resources/Images/Wireframes/ProductModal.png)
When an product is selected, the client will have the option to select the available quantity that they wish to put in the cart and then add that product to the cart.

![User Registration](./Resources/Images/Wireframes/UserResgistration.png)
If the user is not logged in, they will have the option to register for an account. This is paramount for registering pets that might be lost.

![Login](./Resources/Images/Wireframes/Login.png)
One the user has registered for an account, they can now login. Once logged in, the client will have more options to "View Account", "Register Pet", "View Pets", and "Logout". The Client will be able to report a pet missing in the view pets page.

![Register Pet](./Resources/Images/Wireframes/RegisterPet.png)
Once the client is logged in, they can now register a pet. The pet will have a name, and descriptive information as well as some images and videos.

![Pet Map](./Resources/Images/Wireframes/PetMap.png)
All users will have the ability to view the missing pet map. The user will either need to provide a location and radius. 


<a id="functional-requirments-bookmark"></a>
### Functional Requirements

<a id="nonfunctional-requirments-bookmark"></a>
### Non-Functional Requirements

<a id="open-issues-bookmark"></a>
## Open Issues

<a id="references-bookmark"></a>
## References

